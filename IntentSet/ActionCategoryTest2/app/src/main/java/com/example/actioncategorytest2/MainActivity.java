package com.example.actioncategorytest2;

import android.Manifest;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final int PICK_CONTACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                // 创建Intent
                Intent intent = new Intent();
                // 该属性是Action属性：让用户选择数据并返回所选数据
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // 设置Intent的Type属性
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent,PICK_CONTACT);
                 */
                String[] str = new String[]{Manifest.permission.READ_CONTACTS};
                // 运行时获取读取联系人信息的权限
                requestPermissions(str,0x133);
            }
        });
        Button bn_home = (Button)findViewById(R.id.bn_home);
        bn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
    }

    /**
     * 1、最新的Android要求获取系统资源时不能仅靠AndroidManifest.xml文件中申请全向，
     * 而是要求在APP运行时动态请求获取权限，上面的代码是在运行时动态获取权限
     * 2、当用户对应用授权完成后，系统会自动激发Activity的onRequestPermissionsResult方法，因此
     * 上面Activity重写该方法来处理用户的授权结果，只有当用户授权本Activity访问Contacts时，本
     * Activity才会继续定义action为Intent.ACTION_PICK
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == 0x133){
            // 如果用户同意授权访问
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 创建Intent
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                // 设置Intent的Type属性
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                // 启动Activity，并希望获取该Activity的结果
                startActivityForResult(intent,PICK_CONTACT);
            }
        }
    }
    @Override
    public void onActivityResult(int requeseCode, int resultCode, Intent data) {
        super.onActivityResult(requeseCode, resultCode, data);
        switch (requeseCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    // 获取返回的数据
                    Uri contactData = data.getData();
                    CursorLoader cursorLoader = new CursorLoader(this, contactData,
                            null, null, null, null);
                    // 查询联系人信息
                    Cursor cursor = cursorLoader.loadInBackground();
                    // 如果查询到指定的联系人
                    if (cursor.moveToFirst()) {
                        String contactId = cursor.getString(cursor.
                                getColumnIndex(ContactsContract.Contacts._ID));
                        // 获取联人的姓名
                        String name = cursor.getString(cursor.
                                getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "此联系人暂未输入电话号码";
                        // 根据联系人查询该联系人的详细信息
                        Cursor phones = getContentResolver().query(
                                ContactsContract.CommonDataKinds.
                                        Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + "=" + contactId, null, null);
                        if (phones.moveToFirst()) {
                            // 取出电话号码
                            phoneNumber = phones.getString(phones.
                                    getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }
                        // 关闭游标
                        phones.close();
                        EditText show = (EditText) findViewById(R.id.show);
                        // 显示联系人的名称
                        show.setText(name);
                        EditText phone = (EditText) findViewById(R.id.phone);
                        // 显示联系人的电话号码
                        phone.setText(phoneNumber);
                    }
                    // 关闭游标
                    cursor.close();
                }
                break;
        }
    }
}
