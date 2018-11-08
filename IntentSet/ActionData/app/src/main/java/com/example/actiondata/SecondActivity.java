package com.example.actiondata;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class SecondActivity extends TabActivity {
    /**
     * 使用Intent创建Tab页
     *      前面的例子介绍如何使用TabActivity来创建Activity布局，
     *      前面添加Tab页使用TabHost.TabSpec
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 获取该Activity里面的TabHost组件
        TabHost tabHost = getTabHost();
        // 使用Intent添加第一个Tab页面
        tabHost.addTab(tabHost
                .newTabSpec("tab1")
                .setIndicator("已接电话",getResources().getDrawable(R.drawable.ic_launcher_background))
                .setContent(new Intent(this,BeCalledActivity.class)));
        // 使用Intent添加第二个Tab页面
        tabHost.addTab(tabHost.newTabSpec("tab1")
            .setIndicator("呼出电话")
            .setContent(new Intent(this,CalledActivity.class)));
        // 使用Intent添加第三个Tab页面
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("未接电话")
                .setContent(new Intent(this, NoCallActivity.class)));

    }
}
