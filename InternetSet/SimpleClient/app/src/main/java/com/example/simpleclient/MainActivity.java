package com.example.simpleclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (EditText)findViewById(R.id.show);
        new Thread(){
            @Override
            public void run(){
                try {
                    // 建立连接到远程服务器的Socket
                    Socket socket = new Socket("10.168.67.19",3000);
                    // 将Socket对应的输入流包装成BufferedReader
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 进行普通的I/O操作
                    String line = br.readLine();
                    show.setText("来自服务器的数据:"+ line);
                    // 关闭输入流、socket
                    br.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
