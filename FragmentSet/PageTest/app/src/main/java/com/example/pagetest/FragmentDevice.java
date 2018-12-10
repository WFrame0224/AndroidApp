package com.example.pagetest;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentDevice extends Fragment {

    private String[] devices_name;
    private String[] devices_addr;
    private String[] devices_status;
    // 创建进度条
    ProgressDialog pd_search,pd_connect;
    // 创建数据相关ListView和Adapter
    ListView devicesListView;
    SimpleAdapter simpleAdapter;
    TextView connect_show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View deviceView = inflater.inflate(R.layout.fragment_device, container, false);

        return deviceView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        connect_show = (TextView) getView().findViewById(R.id.connect_show);
        connect_show.setVisibility(View.INVISIBLE);

        // 获取数组资源
        devices_name = getResources().getStringArray(R.array.devices_name);
        devices_addr = getResources().getStringArray(R.array.devices_addr);
        devices_status = getResources().getStringArray(R.array.devices_status);
        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < devices_name.length; i++) {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("devices_name",devices_name[i]);
            listItem.put("devices_addr",devices_addr[i]);
            listItem.put("devices_status",devices_status[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.devices_item,
                new String[]{"devices_name","devices_addr","devices_status"},
                new int[]{R.id.devices_name,R.id.devices_addr,R.id.devices_status});
        devicesListView = (ListView)getView().findViewById(R.id.devices_detail);

        // 找到搜索按钮
        Button bn = (Button)getView().findViewById(R.id.devices_search);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示精度条
                pd_search = new ProgressDialog(getActivity());
                pd_search.setTitle("正在查找蓝牙设备");
                pd_search.setIcon(R.drawable.searching);
                pd_search.setMessage("搜索设备中....\r\n按 返回 即可结束");
                pd_search.setCancelable(true);
                pd_search.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd_search.show();
                // 精度条完成后添加数据，此处为模拟数据，实际是蓝牙扫描得到的数据
                devicesListView.setAdapter(simpleAdapter);
                // 10s扫描之后，若未按返回则关闭精度条
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pd_search.dismiss();
                    }
                },10000);
            }
        });
        // 为ListView的列表项的蓝牙设备绑定事件监听器
        devicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                pd_connect = new ProgressDialog(getActivity());
                pd_connect.setTitle("连接设备");
                pd_connect.setIcon(R.drawable.get);
                pd_connect.setMessage("正在连接 " + devices_name[position]);
                pd_connect.setCancelable(false);
                pd_connect.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd_connect.show();
                // 10s扫描之后，若未按返回则关闭精度条
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pd_connect.dismiss();
                    }
                },200);
                connect_show.setVisibility(View.VISIBLE);
                connect_show.setText("连接的设备为：\n"+devices_name[position] + "\n" +
                        devices_addr[position]);

            }
        });

    }


}
