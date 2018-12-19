package com.example.parcelableserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParcelableService extends Service {
    static HashMap<Person,List<Pet>> pets ;
    private PetBinder petBinder;
    public class PetBinder extends IPet.Stub{
        @Override
        public List<Pet> getPets(Person owner){
            // 返回Service内部的数据
            return pets.get(owner);
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        petBinder = new PetBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        pets = new HashMap<Person,List<Pet>>();
        // 初始化pets Map集合
        ArrayList<Pet> list1 = new ArrayList<>();
        list1.add(new Pet("旺财",4.3));
        list1.add(new Pet("来福",5.1));
        pets.put(new Person(1,"sun","sun"),list1);
        ArrayList<Pet> list2 = new ArrayList<>();
        list2.add(new Pet("kitty",2.3));
        list2.add(new Pet("garfield",3.1));
        pets.put(new Person(2,"bai","bai"),list2);
        Log.d("cy", "success bind");
        /* 返回catBinder对象
         * 在绑定本地Service的情况下，该catBinder对象会直接
         * 传给客户端的ServiceConnection对象
         * 的onServiceConnected方法的第二个参数；
         * 在绑定远程Service的情况下，只将catBinder对象的代理
         * 传给客户端的ServiceConnection对象
         * 的onServiceConnected方法的第二个参数；
         */
        return petBinder;
    }
}
