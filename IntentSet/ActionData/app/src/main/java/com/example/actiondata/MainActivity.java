package com.example.actiondata;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

/**
 * 一旦为Intent同时指定了ACtion、Data属性，Android就可以根据指定的数据类型来
 * 启动特定的应用程序，并对hiding数据执行相应的操作
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button) findViewById(R.id.bn);
        Button edit = (Button) findViewById(R.id.edit);
        Button call = (Button) findViewById(R.id.call);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String data = "http://www.baidu.com";
                Uri uri = Uri.parse(data);
                // 为Intent设置Action属性
                intent.setAction(Intent.ACTION_VIEW);
                // 设置Data属性
                intent.setData(uri);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 为Intent设置Action属性（动作为：编辑）
                intent.setAction(Intent.ACTION_VIEW);
                String data = "content://com.android.contacts/contacts/";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 为Intent设置Action属性（动作为：拨号）
                intent.setAction(Intent.ACTION_DIAL);
                String data = "tel:15829586286";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
    public void openTab(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("frame://www.frame.com:0224/mypath"));
        startActivity(intent);
    }
}
