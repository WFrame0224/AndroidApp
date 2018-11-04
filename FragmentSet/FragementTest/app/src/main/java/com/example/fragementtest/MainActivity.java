package com.example.fragementtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // 实现Callbacks接口必须实现的方法
    @Override
    public void onItemSelected(Integer id){
        // 创建Bundle,准备向Fragment传入参数
        // 正如Activity之间交换数据，利用Intent作为数据通道，Bundle作为数据包的载体，Fragment中也如此
        Bundle arguments = new Bundle();
        arguments.putInt(BookDetailFragment.ITEM_ID,id);
        // 创建BookDetailFragment对象
        BookDetailFragment fragment = new BookDetailFragment();

        /** Activity向Fragment传递参数：
         *  在Activity中创建Bundle数据包，并调用Fragment的SetArguments(Bundle bundle)方法即可将Bundle
         *  数据包传递给Fragment
         */
        // 向Fragment传入数据参数，用Bundle包装的
        fragment.setArguments(arguments);
        /**
         * Activity的getFragmentManager()方法返回FragmentManger,FragmentManger对象的
         *  beginTransaction()方法即可开启并返回FragmentTransaction对象
         */
        // 使用Fragment替换book_detail_container容器当前显示的Fragment
        getFragmentManager().beginTransaction().replace(R.id.book_detail_container,fragment).commit();
    }
}
