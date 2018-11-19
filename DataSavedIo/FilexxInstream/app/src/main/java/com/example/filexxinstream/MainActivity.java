package com.example.filexxinstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "frame.txt";
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
            // 打开文件输入流
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            // 读取文件内容
            while ((hasRead = fis.read(buff)) > 0){
                sb.append(new String(buff,0,hasRead));
            }
            // 关闭文件输入流
            fis.close();
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private void write(String content){
        try{
            // 以追加模式打开文件输入流
            FileOutputStream fos = openFileOutput(FILE_NAME,MODE_APPEND);
            PrintStream ps = new PrintStream(fos);
            // 输出文件内容
            ps.println(content);
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
