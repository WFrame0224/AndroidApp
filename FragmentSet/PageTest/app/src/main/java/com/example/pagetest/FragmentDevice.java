package com.example.pagetest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentDevice extends Fragment {

    private String[] devices_name;
    private String[] devices_addr;
    private String[] devices_status;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View deviceView = inflater.inflate(R.layout.fragment_device, container, false);
        return deviceView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
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
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.devices_item,
                new String[]{"devices_name","devices_addr","devices_status"},
                new int[]{R.id.devices_name,R.id.devices_addr,R.id.devices_status});
        ListView listView = (ListView)getView().findViewById(R.id.devices_detail);
        listView.setAdapter(simpleAdapter);
    }
}
