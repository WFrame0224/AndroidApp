package com.example.asynctasktest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 创建异步任务AsyncTask需要3步：
 * 1、创建AsyncTask的子类，并未三个泛型参数指定类型。
 * 2、根据需要，实现AsyncTask的如下方法
 * 1）doInBackgroud()
 * 2)onProgressUpdate()
 * 3)onPreExecute()
 * 4)onPostExecute()
 * 3、调用AsyncTask子类的实例的execute()开始执行耗时任务
 */

public class MainActivity extends AppCompatActivity {

    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.textView);
    }

    public void download(View view) throws MalformedURLException {
        DownTask task = new DownTask(this);
        task.execute(new URL("http://www.crazyit.org/index.php"));
    }

    class DownTask extends AsyncTask<URL, Integer, String> {
        // 可变长的输入参数，与AsyncTask.exucute()对应
        ProgressDialog progressdialog;
        // 定义记录已经读取行的数量
        int hasRead = 0;
        Context mContext;
        public DownTask(Context ctx){
            mContext = ctx;
        }
        // 该方法实现实际的下载任务
        @Override
        protected String doInBackground(URL... params){
            StringBuilder sb = new StringBuilder();
            try {
                URLConnection coon = params[0].openConnection();
                // 打开coon连接对应的输入流，并将它包装成BufferedReader
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(coon.getInputStream(),
                                "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null){
                    sb.append(line + "\n");
                    hasRead ++;
                    publishProgress(hasRead);
                }
                return sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        // 该方法实现：负责当下载完成后，将下载的代码显示出来
        @Override
        protected void onPostExecute(String result){
            // 返回HTML页面的内容
            show.setText(result);
            progressdialog.dismiss();
        }
        // 该方法实现在下载时显示进度条
        @Override
        protected void onPreExecute(){
            progressdialog = new ProgressDialog(mContext);
            // 设置对话框标题
            progressdialog.setTitle("任务正在执行中");
            // 设置对话框显示的内容
            progressdialog.setMessage("任务正在执行中，敬请等待");
            // 设置对话框不能用“取消”按钮关闭
            progressdialog.setCancelable(false);
            // 设置该进度条的最大进度值
            progressdialog.setMax(202);
            // 设置对话框的进度条风格
            progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // 设置对话框的进度条是否显示进度
            progressdialog.setIndeterminate(false);
            progressdialog.show();
        }
        // 该方法实现随着下载进度的改变更新进度条的进度值
        @Override
        protected void onProgressUpdate(Integer... values){
            // 更新进度
            show.setText("已经读取了【" + values[0] + "】行！");
            progressdialog.setProgress(values[0]);
        }
    }
}
