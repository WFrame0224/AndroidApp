package com.example.adapterviewflipperui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] imageIds = new int[]{
            R.drawable.shangzi,R.drawable.shuangyu,R.drawable.chunv,
            R.drawable.tiancheng, R.drawable.tianxie,R.drawable.sheshou,
            R.drawable.juxie, R.drawable.shuiping,R.drawable.shizi,
            R.drawable.baiyang, R.drawable.jinniu,R.drawable.mojie
    };
    private AdapterViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipper = (AdapterViewFlipper)findViewById(R.id.fliper);
        // 创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
            // 该方法返回的View代表每个列表项
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // 创建一个ImageView
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(imageIds[position]);
                // 设置ImageView的缩放参数
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                // 为imageView设置布局参数
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }

    public void prev(View view){

        // 显示上一个组件
        flipper.showPrevious();
        // 停止自动播放
        flipper.stopFlipping();
    }
    public void next(View view){

        // 显示下一个组件
        flipper.showNext();
        // 停止自动播放
        flipper.stopFlipping();
    }
    public void auto(View view){

        //开始自动播放
        flipper.startFlipping();
    }
}
