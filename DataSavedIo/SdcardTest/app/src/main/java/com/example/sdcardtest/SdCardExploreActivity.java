package com.example.sdcardtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SdCardExploreActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    // 记录当前的父文件夹
    File currentParent;
    // 记录当前路径下的所有文件的文件数组
    File[] currentFiles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card_explore);
        // 运行时获取写入SD卡的权限
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x123);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                              @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if (requestCode == 0x123){
            // 如果用户同意授权访问
            if (permissions.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                // 获取列出全部文件的ListView
                listView = (ListView) findViewById(R.id.list);
                textView = (TextView) findViewById(R.id.path);
                // 获取系统的SD卡的目录
                File root = new File(Environment.getExternalStorageDirectory().getPath());
                // 如果SD卡存在
                if (root.exists()) {
                    currentParent = root;
                    currentFiles = root.listFiles();
                    // 使用当前目录下的全部文件、文件夹来填充ListView
                    inflateListView(currentFiles);
                }
                // 为ListView的列表项的单击事件绑定监视器
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        // 用户单击了文件，直接返回，不做任何处理
                        if (currentFiles[position].isFile()) return;
                        // 获取用户点击的文件夹下的所有文件
                        File[] tmp = currentFiles[position].listFiles();
                        if (tmp == null || tmp.length == 0) {
                            Toast.makeText(SdCardExploreActivity.this,
                                    "当前路径不可访问，或该路径下没有文件", Toast.LENGTH_SHORT).show();
                        } else {
                            // 获取用户单击列表的列表项对应的文件夹，设为当前的父文件夹
                            currentParent = currentFiles[position];
                            // 保存当前的父文件夹内的全部问价和文件夹
                            currentFiles = tmp;
                            // 再次更新ListView
                            inflateListView(currentFiles);
                        }
                    }
                });
                // 获取上一级目录的按钮
                Button parent = (Button) findViewById(R.id.parent);
                parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (!currentParent.getCanonicalPath().equals(Environment.getExternalStorageDirectory().getPath())) {
                                // 获取上一级目录
                                currentParent = currentParent.getParentFile();
                                // 列出当前目录下所有文件
                                currentFiles = currentParent.listFiles();
                                // 再次更新ListView
                                inflateListView(currentFiles);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else {
                // 提示用户必须允许写入SD卡的权限
                Toast.makeText(this,
                        "本程序要求您必须授予程序写入SD卡的权限,您可以打开Settings→Apps and notifications为App设置权限",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void inflateListView(File[] files) {
        // 创建一个List集合，List集合的元素时Map
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            // 如果当前file是问价夹，使用folder图标，否则使用file图标
            if (files[i].isDirectory()) {
                listItem.put("icon", R.drawable.folder);
            } else {
                listItem.put("icon", R.drawable.file);
            }
            listItem.put("fileName", files[i].getName());
            // 添加List项
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.line,
                new String[]{"icon", "fileName"},
                new int[]{R.id.icon, R.id.file_name});
        // 为ListView设置Adapter
        listView.setAdapter(simpleAdapter);
        try {
            textView.setText("当前路径为：" + currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
