package com.example.imageuitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.QuickContactBadge;

public class OpenQuickContactActivity extends AppCompatActivity {

    QuickContactBadge badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_contact_activity);

        // 获取QuickContactBadge组件
        badge = (QuickContactBadge)findViewById(R.id.badge);
        // 将QuickContactBadge组件与特定电话号码对应的联系人建立关联
        badge.assignContactFromPhone("020-66666666",false);
    }
}
