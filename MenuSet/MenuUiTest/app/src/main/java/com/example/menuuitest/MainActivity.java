package com.example.menuuitest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 定义字体大小菜单项的标识
    final int FONT_10 = 0x111;
    final int FONT_12 = 0x112;
    final int FONT_14 = 0x113;
    final int FONT_16 = 0x114;
    final int FONT_18 = 0x115;

    // 定义字体颜色菜单项的标识
    final int FONT_RED = 0x116;
    final int FONT_BLUE = 0x117;
    final int FONT_GREEN = 0x118;
    
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.txt);
    }

    // 重写Activity的onCreatOptionsMenu(Menu menu)方法，在该方法中调用Menu应用添加菜单和子菜单
    // 当用户单击MENU键时触发该方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 向menu中添加普通菜单项
        SubMenu other = menu.addSubMenu("启动程序");
        other.setHeaderIcon(R.drawable.tools);
        other.setHeaderTitle("选择您要启动的程序");
        // 添加菜单项
        MenuItem item = other.add("查看Swift");
        // 为菜单项设置关联的Activity
        // 要想此处起作用，必须将OnonOptionsItemSelected方法屏蔽，即不能重写，这样才能成功
//        item.setIntent(new Intent(this,OtherActivity.class));

        // 向menu中添加字体大小的子菜单
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        // 设置菜单头的图标
        fontMenu.setHeaderIcon(R.drawable.font);
        // 设置菜单头的标题
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0, FONT_10, 0, "10号字体");
        fontMenu.add(0, FONT_12, 0, "12号字体");
        fontMenu.add(0, FONT_14, 0, "14号字体");
        fontMenu.add(0, FONT_16, 0, "16号字体");
        fontMenu.add(0, FONT_18, 0, "18号字体");

        // 向menu中添加文字颜色的子菜单
        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        colorMenu.setIcon(R.drawable.color);
        // 设置菜单头的图标
        colorMenu.setHeaderTitle("选择文字颜色");
        colorMenu.add(0, FONT_RED, 0, "红色");
        colorMenu.add(0, FONT_GREEN, 0, "绿色");
        colorMenu.add(0, FONT_BLUE, 0, "蓝色");

        return super.onCreateOptionsMenu(menu);
    }

    // 如果希望应用程序能够响应菜单项的单击事件，那么重写Activity的onOptionsItemsSelected(MenuItem mi)方法
    // 选项菜单的菜单项被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        // 判断单击的是哪个菜单项，并针对性的做出响应
        switch (mi.getItemId()) {
            case FONT_10:
                edit.setTextSize(10 * 2);
                break;
            case FONT_12:
                edit.setTextSize(12 * 2);
                break;
            case FONT_14:
                edit.setTextSize(14 * 2);
                break;
            case FONT_16:
                edit.setTextSize(16 * 2);
                break;
            case FONT_18:
                edit.setTextSize(18 * 2);
                break;
            case FONT_RED:
                edit.setTextColor(Color.RED);
                break;
            case FONT_GREEN:
                edit.setTextColor(Color.GREEN);
                break;
            case FONT_BLUE:
                edit.setTextColor(Color.BLUE);
                break;
        }
        return true;
    }
}
