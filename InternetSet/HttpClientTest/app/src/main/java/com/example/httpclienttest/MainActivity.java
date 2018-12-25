package com.example.httpclienttest;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView response;
    private OkHttpClient okHttpClient;

    public class MyHandler extends Handler{
        private WeakReference<MainActivity> mWeakReferenceActivity;
        MyHandler(MainActivity activity){
            mWeakReferenceActivity = new WeakReference<MainActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                // 使用response文本框显示服务器响应
                response.append(msg.obj.toString() + "\n");
            }
        }
    }
    private MyHandler handler = new MyHandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        // 创建默认的OkHttpClient对象
        final HashMap<String,List<Cookie>> cookieStore = new HashMap<>();
//        okHttpClient = new OkHttpClient();
        // 为了维持Session,即维持长时间连接，采用下面的机制
        // 文章：https://blog.csdn.net/chen19960724/article/details/52355820，很好
        CookieJar cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                cookieStore.put(httpUrl.host(),list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();

    }

    public void accessSecret(View view) {
        new Thread(){
          @Override
          public void run(){
              String url = "http://10.168.67.19:8080/foo/secret.jsp";
              // 创建请求
              Request request = new Request.Builder().
                      url(url).
                      build();
              try {
                  Response response = okHttpClient.newCall(request).execute();
                  Message msg = new Message();
                  msg.what = 0x123;
                  msg.obj = response.body().string();
                  handler.sendMessage(msg);
              } catch (Exception e) {
                  e.printStackTrace();
              }

          }
        }.start();
    }

    public void showLogin(View view) {
        // 加载登录界面
        final View loginDialog = getLayoutInflater().inflate(R.layout.login,null);
        // 使用对话框供用户登录系统
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("登录系统")
                .setView(loginDialog)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 获取用户输入的用户名、密码
                        String name = ((EditText)loginDialog.findViewById(R.id.name))
                                .getText().toString();
                        String pass = ((EditText)loginDialog.findViewById(R.id.pass))
                                .getText().toString();
                        String url = "http://10.168.67.19:8080/foo/login.jsp";
                        FormBody body = new FormBody.Builder().add("name",name).add("pass",pass)
                                .build();
                        Request request = new Request.Builder().
                                url(url).
                                post(body)
                                .build();
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Looper.prepare();
                                Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        });
                    }
                }).setNegativeButton("取消",null).show();
    }
}
