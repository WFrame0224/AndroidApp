package com.example.pagetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mainActivityViewPaper;
    BottomNavigationView bottomNavView;
    MainActivityViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取到两个控件
        mainActivityViewPaper = (ViewPager)findViewById(R.id.main_viewpaper);
        bottomNavView = (BottomNavigationView) findViewById(R.id.navigation);
        // 为ViewPaper设置Adapter
        adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());
        // 为Adapter添加Fragment
        adapter.addFragment(new FragmentDevice());
        adapter.addFragment(new FragmentScore());
        adapter.addFragment(new FragmentProject());
        adapter.addFragment(new FragmentPerson());
        mainActivityViewPaper.setAdapter(adapter);

        // 去掉menu大于3个后的动画
        BottomNavigationViewHelper.disableShiftMode(bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_device:
                        mainActivityViewPaper.setCurrentItem(0);
                        break;
                    case R.id.navigation_score:
                        mainActivityViewPaper.setCurrentItem(1);
                        break;
                    case R.id.navigation_project:
                        mainActivityViewPaper.setCurrentItem(2);
                        break;
                    case R.id.navigation_person:
                        mainActivityViewPaper.setCurrentItem(3);
                        break;


                }
                return true;
            }
        });
        // 为ViewPaper设置监听事件
        mainActivityViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当View滑动后设置naBottomNavigationView 选中相应选项
                bottomNavView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
