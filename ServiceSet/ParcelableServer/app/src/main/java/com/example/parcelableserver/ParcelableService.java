package com.example.parcelableserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ParcelableService extends Service {
    private ArrayList<Person> persons ;
    @Override
    public IBinder onBind(Intent intent) {
        persons=new ArrayList<Person>();
        Log.d("cy", "success bind");
        return iBinder;
    }
    private IBinder iBinder = new IPerson.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            persons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("cy", "onCreate ");
    }
}
