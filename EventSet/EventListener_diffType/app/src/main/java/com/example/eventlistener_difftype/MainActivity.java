package com.example.eventlistener_difftype;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText address;
    EditText content;
    TextView show;
    Button bn1,bn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取页面中收件人地址、短信内容
        address = (EditText) findViewById(R.id.address);
        content = (EditText) findViewById(R.id.content);
        bn1 = (Button) findViewById(R.id.send);
        // 使用外部类的实例作为事件监听器
        bn1.setOnLongClickListener(new SendSmsListener(
                this , address.getText().toString(), content.getText().toString()));
        show = (TextView)findViewById(R.id.textView);
        bn2 = (Button)findViewById(R.id.button2);
        bn2.setOnClickListener(this);
    }

    // Activity本身作为事件监听器类
    @Override
    public void onClick(View view){
        show.setText("按钮被单击了");
    }
}
