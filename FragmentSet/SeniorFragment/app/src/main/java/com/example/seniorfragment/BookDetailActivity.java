package com.example.seniorfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定加载/res/layout目录下的activity_book_detail.xml布局文件
        // 该界面布局文件内只定义了一个名为book_detail_container的FrameLayout
        setContentView(R.layout.activity_book_detail);
        if (savedInstanceState == null) {
            // 创建BookDetailFragment对象
            BookDetailFragment fragment = new BookDetailFragment();
            // 创建Bundle对象
            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID,
                    getIntent().getIntExtra(BookDetailFragment.ITEM_ID, 0));
            // 向Fragment传入参数
            fragment.setArguments(arguments);
            // 将制定fragment添加到book_detail_container容器中
            getFragmentManager().beginTransaction().add(R.id.book_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            // 创建启动BookListActivity的Intent
            Intent intent = new Intent(this,MainActivity.class);
            // 添加额外的Flag,将Activity栈中处于FirstActivity之上的Activity弹出
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // 启动对应的intent对应的Activity
            startActivity(intent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}
