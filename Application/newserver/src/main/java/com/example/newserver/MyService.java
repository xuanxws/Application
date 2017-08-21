package com.example.newserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public class MyService extends Service {

    ArrayList<Person> mPersonArrayList = new ArrayList<>();

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            if (null == mPersonArrayList) {
                mPersonArrayList = new ArrayList<>();
            }
            mPersonArrayList.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            if (null != mPersonArrayList) {
              return mPersonArrayList;
            }
            return null;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
