package com.example.myserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.myclient.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public class MyService extends Service {

    private List<Person> mPersonList = new ArrayList<>();

    Stub mStub = new Stub() {
        @Override
        public void addPerson(Person person) {
            if (null == mPersonList) {
                mPersonList = new ArrayList<>();
            }
            if (null != person) {
                mPersonList.add(person);
            }
        }

        @Override
        public List<Person> getPerson() {
            if (null != mPersonList) {
                return mPersonList;
            }
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
