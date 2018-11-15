package com.example.arrayrestest;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 获取系统定义的数组资源
    String[] texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texts = getResources().getStringArray(R.array.string_arr);
        // 创建一个BaseAdapter对象
        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return texts.length;
            }

            @Override
            public Object getItem(int i) {
                return texts[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }
            // 重写该方法，该方法返回的View将作为GridView的每个格子
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView text = new TextView(MainActivity.this);
                Resources res = MainActivity.this.getResources();
                // 使用尺寸资源来设置文本框的高度、宽度
                text.setWidth((int) res.getDimension(R.dimen.cell_width));
                text.setHeight((int)res.getDimension(R.dimen.cell_height));
                // 使用字符串资源来设置文本框的内容
                text.setText(texts[i]);
                TypedArray icons = res.obtainTypedArray(R.array.plain_arr);
                // 使用颜色资源来设置文本框的背景色
                text.setBackground(icons.getDrawable(i));
                text.setTextSize(20);
                return text;
            }
        };
        GridView grid = (GridView)findViewById(R.id.grid01);
        grid.setAdapter(ba);
    }
}
