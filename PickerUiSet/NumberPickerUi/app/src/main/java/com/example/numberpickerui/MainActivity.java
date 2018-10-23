package com.example.numberpickerui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int minPrice = 25;
    private int maxPrice = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker np1 = (NumberPicker)findViewById(R.id.np1);
        // 设置np1的最小值和最大值
        np1.setMinValue(10);
        np1.setMaxValue(50);
        // 设置np1的当前值
        np1.setValue(minPrice);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                minPrice = newVal;
                showSelectedPrice();
            }
        });
        NumberPicker np2 = (NumberPicker)findViewById(R.id.np2);
        // 设置np2的最小值和最大值
        np2.setMinValue(60);
        np2.setMaxValue(100);
        // 设置np2的当前值
        np2.setValue(maxPrice);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                // 当NumberPicker的值发生改变是，将会激发该方法
                maxPrice = newVal;
                showSelectedPrice();
            }
        });

    }

    private void showSelectedPrice() {

        String msg = "您选择的最低价格为：" + minPrice + "，最高价格为：" + maxPrice;
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

}
