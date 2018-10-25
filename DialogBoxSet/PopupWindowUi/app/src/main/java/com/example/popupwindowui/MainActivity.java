package com.example.popupwindowui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * 此程序实现使用PopupWindows创建对话框风格的窗口，步骤如下所示：
 * 1.调用PopupWindow的构造器创建popupWindow对象
 * 2.调用PopupWindow的showAsDropDown(View v)将PopupWindow作为V组件的下拉组件显示出来，
 * 或调用PopupWindow的showAtLocation()方法将PopupWindow在指定位置显示出来
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 装载R.layout.popup对应的界面布局
        View root = this.getLayoutInflater().inflate(R.layout.popup, null);
        // 1 创建PopupWindow对象
        final PopupWindow popup = new PopupWindow(root, 560, 720);
        Button button = (Button) findViewById(R.id.bn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2 以下拉方式显示
//                popup.showAsDropDown(view);
                // 将PopupWindow显示在指定位置
                popup.showAtLocation(findViewById(R.id.bn), Gravity.CENTER, 20, 20);
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

        /** 设置日期和时间选择的对话框步骤如下：
         * 1.通过new 关键字创建DatePickerDialog TimePickerDialog 实例，调用他们的show方法即可将其
         * 日期选择对话框、时间选择对话框显示出来
         * 2.为DatePickerDialog TimePickerDialog绑定监视器，这样可以保证用户通过DatePickerDialog
         * TimePickerDialog设置事件时触发器，从而通过监视器来获取用户监视的事件
         */
        Button dateBn = (Button) findViewById(R.id.dateBn);
        Button timeBn = (Button) findViewById(R.id.timeBn);
        // 为“设置日期”按钮绑定监听器
        dateBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePicker对话框实例，并将它显示出来
                new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                EditText show = (EditText) findViewById(R.id.show);
                                show.setText("您选择了：" + year + "年" + (month + 1)
                                        + "月" + dayOfMonth + "日");
                            }
                        }
                        //设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //为“设置时间”按钮绑定监听器
        timeBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                Calendar c = Calendar.getInstance();
                // 创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(MainActivity.this,
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hourOfDay,
                                                  int minute) {
                                EditText show = (EditText) findViewById(R.id.show);
                                show.setText("您选择了：" + hourOfDay + "时"
                                        + minute + "分");
                            }
                        }
                        //设置初始时间
                        , c.get(Calendar.HOUR_OF_DAY)
                        , c.get(Calendar.MINUTE)
                        //true表示采用24小时制
                        , true).show();
            }
        });
    }
}
