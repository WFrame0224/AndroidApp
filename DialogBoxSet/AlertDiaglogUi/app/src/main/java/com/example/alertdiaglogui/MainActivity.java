package com.example.alertdiaglogui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.net.URI;

/** 使用AlertDiaglog创建对话框的步骤
 * 1.创建AlertDiaglog.Builder对象
 * 2.调用AlertDiaglog.Builder的setTitle()或setCustomTitle()方法设置标题
 * 3.调用AlertDiaglog.Builder的setIcon方法设置图标
 * 4.调用AlertDiaglog.Builder的相关设置方法设置对话框内容
 * 5.调用AlertDiaglog.Builder的setpositiveButoon(),setNegativeButton()
 *      或setNeutralButton()方法添加多个按钮
 * 6.调用AlertDiaglog.Builder的creat()方法创建AlertDialog对象，
 *      再调用AlertDialog对象的show()方法将对话框显示出来
 */

public class MainActivity extends AppCompatActivity {
    TextView show;
    String[] items = new String[] {
            "疯狂Java讲义", "疯狂Ajax讲义",
            "轻量级Java EE企业应用实战",
            "疯狂Android讲义" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.show);
    }

    public void showMessage(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("简单对话框");
        builder.setIcon(R.drawable.tools);
        builder.setMessage("对话框的测试内容\n第二行内容");
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder);
        // 调用AlertDiaglog.Builder的creat()方法创建AlertDialog对象，再调用AlertDialog对象的show()方法将对话框显示出来
        builder.create().show();
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        // 调用setPositionButton方法添加“确定”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                show.setText("单击了【取消】按钮");
            }
        });
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        // 调用setPositionButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                show.setText("单击了【确定】按钮");
            }
        });
    }

    public void showItems(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 设置对话框标题
                .setTitle("简单列表对话框")
                // 设置图标
                .setIcon(R.drawable.tools);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                show.setText("你选中了《" + items[i] + "》");
            }
        });
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void showSingleChoiceItems(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 设置对话框标题
                .setTitle("单选列表项对话框")
                // 设置图标
                .setIcon(R.drawable.tools);
        // 设置单选列表项，默认选中第二项(索引为1)
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                show.setText("你选中了《" + items[i] + "》");
            }
        });
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void showMultiChoiceItems(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.tools)
                .setTitle("多选列表项对话框");
        // 设置多选列表项，设置为勾选第二项，第四项
        builder.setMultiChoiceItems(items,new boolean[]{false,true,false,true},null);
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder)
                .create()
                .show();
    }

    public void showAdapter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 设置对话框标题
                .setTitle("多选列表项对话框")
                // 设置图标
                .setIcon(R.drawable.tools);
        // 设置自定义列表项
        builder.setAdapter(new ArrayAdapter<String>(this,R.layout.array_item,items),null);
        setPositiveButton(builder);
        setNegativeButton(builder);
        builder.create().show();
    }

    public void showView(View view) {
        // 装载app\src\main\res\layout\login.xml界面布局文件
        TableLayout loginForm = (TableLayout)getLayoutInflater().inflate(R.layout.login_activity,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.tools)
                .setTitle("自定义View对话框");
        // 设置对话框显示的View对象
        builder.setView(loginForm);
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 此处可进行登录处理
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 取消登录，不做任何处理
            }
        });
        builder.create();
        builder.show();
    }
}
