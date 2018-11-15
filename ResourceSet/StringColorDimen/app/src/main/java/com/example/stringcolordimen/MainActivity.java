package com.example.stringcolordimen;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 使用字符串资源
    int[] texIds = new int[]{
            R.string.c1, R.string.c2, R.string.c3,
            R.string.c4, R.string.c5, R.string.c6,
            R.string.c7, R.string.c8, R.string.c9
    };
    int[] colorsIds = new int[]{
            R.color.c1, R.color.c2, R.color.c3,
            R.color.c4, R.color.c5, R.color.c6,
            R.color.c7, R.color.c8, R.color.c9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建一个BaseAdapter对象
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                // 指定一共包含9个选项
                return texIds.length;
            }

            @Override
            public Object getItem(int position) {
                // 返回指定位置的文本
                return getResources().getText(texIds[position]);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            // 重写该方法，该方法返回的View将作为GridView的每个格子
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(MainActivity.this);
                Resources res = MainActivity.this.getResources();
                // 使用尺度资源来设置文本框的高度、宽度
                textView.setWidth((int) res.getDimension(R.dimen.cell_width));
                textView.setHeight((int) res.getDimension(R.dimen.cell_height));
                // 使用字符串资源来设置文本框中的内容
                textView.setText(texIds[position]);
                // 使用颜色资源来设置文本框的背景色
                textView.setBackgroundResource(colorsIds[position]);
                textView.setTextSize(20);
                textView.setTextSize(getResources().getInteger(R.integer.font_size));
                return textView;
            }
        };
        GridView gridView = (GridView)findViewById(R.id.grid01);
        gridView.setAdapter(baseAdapter);
    }
}
