package com.example.multithreadclient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView show;
    Button send;
    Handler handler;
    // 定义与服务器通信的子线程
    ClientTherad clientTherad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.Client_msg);
        send = (Button)findViewById(R.id.btn_send);
        show = (TextView)findViewById(R.id.Server_msg);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                // 如果消息来自于子线程
                if (msg.what == 0x123){
                    // 将读取的内容追加显示在文本框中
                    show.append("\n" + msg.obj.toString());
                }
            }
        };
        clientTherad = new ClientTherad(handler);
        // 客户端启动ClientThread线程创建网络连接，读取来自服务器的数据
        new Thread(clientTherad).start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    // 当用户按下发送按钮后，将用户输入数据封装成Message
                    // 然后发送给子线程的Handler
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = input.getText().toString();
                    clientTherad.revHandler.sendMessage(msg);
                    // 清空input文本框
                    input.setText("");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
