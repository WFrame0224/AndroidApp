package com.example.viewswitcheruidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageSwitcherActivity extends AppCompatActivity {

    int[] imageIds = new int[]{
            R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
            , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
            , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
            , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
    };
    ImageSwitcher switcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建一个List对象，List对象的元素时Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < imageIds.length;i++) {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        // 获取图片显示的ImageSwitcher
        switcher = (ImageSwitcher)findViewById(R.id.image_switcher);
        // 为ImageSwitcher设置图片切换的的动画效果
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                // 创建ImageView对象
                ImageView imageView = new ImageView(ImageSwitcherActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                // 返回ImageView对象
                return imageView;
            }
        });
    }
}
