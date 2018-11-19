package com.example.sdcardtest;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {

    /**
     * 读写SD卡文件需要按照如下步骤
     * 1）调用Environment的getExternalStorageState()方法判断手机上是否插入了SD卡，
     *      并且应用程序具有了读写SD卡的权限
     * 2）调用Environment的getExternalStorageDirectory方法来获取外部存储器，也就是SD卡目录
     * 3）使用FileInputStream,FileOutputStream,FileReader或FileWriter读写SD卡的文件
     */

    final String FILE_NAME = "/frame.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(new StringBuilder("a").append("b").append("c").toString());
        // 获取两个按钮
        final Button read = (Button)findViewById(R.id.bn_read);
        final Button write = (Button)findViewById(R.id.bn_write);
        // 获取两个文本框
        final EditText edit_write = (EditText)findViewById(R.id.edit_write);
        final EditText edit_read = (EditText)findViewById(R.id.edit_read);
        // 为write按钮绑定事件监听器
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 将edit_write中的内容写入文件中
                write(edit_write.getText().toString());
                edit_write.setText("");
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 读取指定文件中的内容并显示出来
                edit_read.setText(read());
            }
        });
    }
    private String read(){
        try {
           // 如果手机插入了SD卡，而且应用程序具有访问SD卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                // 获取SD卡对应的存储目录
                File sdCardDir = Environment.getExternalStorageDirectory();
                System.out.println("----------" + sdCardDir);
                // 获取指定文件对应的输入流
                FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath()+ FILE_NAME);
                // 将指定输入流包装成BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder("");
                String line = null;
                // 循环读取文件内容
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private void write(String content){
        try{
            // 如果手机插入了SD卡，而且应用程序具有访问SD卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                // 获取SD卡的目录
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + FILE_NAME);
                // 以指定文件创建 RandomAcessFile对象
                RandomAccessFile raf = new RandomAccessFile(targetFile,"rw");
                // 将文件记录指针移动到最后
                raf.seek(targetFile.length());
                // 输出文件内容
                raf.write(content.getBytes());
                // 关闭RadomAccessFile
                raf.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openSdCradExplore(View view) {
        Intent intent = new Intent(MainActivity.this,SdCardExploreActivity.class);
        startActivity(intent);
    }
}
