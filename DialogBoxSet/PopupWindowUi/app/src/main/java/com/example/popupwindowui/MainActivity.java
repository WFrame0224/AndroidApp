package com.example.popupwindowui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * 此程序实现使用PopupWindows创建对话框风格的窗口，步骤如下所示：
 * 1.调用PopupWindow的构造器创建popupWindow对象
 * 2.调用PopupWindow的showAsDropDown(View v)将PopupWindow作为V组件的下拉组件显示出来，
 *      或调用PopupWindow的showAtLocation()方法将PopupWindow在指定位置显示出来
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 装载R.layout.popup对应的界面布局
        View root = this.getLayoutInflater().inflate(R.layout.popup,null);
        // 1 创建PopupWindow对象
        final PopupWindow popup = new PopupWindow(root,560,720);
        Button button = (Button)findViewById(R.id.bn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2 以下拉方式显示
//                popup.showAsDropDown(view);
                // 将PopupWindow显示在指定位置
                popup.showAtLocation(findViewById(R.id.bn),Gravity.CENTER,20,20);
            }
        });
        // 获取PopupWindow中的“关闭”按钮
        root.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关闭PopupWindow
                popup.dismiss();
            }
        });
    }
}
