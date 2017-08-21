package com.example.myclient;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public class MyProXy {

    public static final int ADD = 0;
    public static final int GET = 1;

    private static IBinder mRemote;
    private static MyProXy myProXy;

    public MyProXy() {
    }

    public static MyProXy getInstance(IBinder binder) {
        myProXy = new MyProXy();
        mRemote = binder;
        return myProXy;
    }

    /**
     * 添加一个英雄
     * @param person
     */
    public void addPerson(Person person) {

        Parcel _write = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        if (null != person) {
            _write.writeInt(1);
            person.writeToParcel(_write, 0);
        } else {
            _write.writeInt(0);
        }
        try {
            mRemote.transact(ADD, _write, _reply, 0);
            _reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            _write.recycle();
            _reply.recycle();
        }
    }

    /**
     * 获取英雄列表
     * @return
     */
    public List<Person> getPersonList(){
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        List<Person> mList = new ArrayList<>();

        try {
            mRemote.transact(GET,_data,_reply,0);
            _reply.readException();
            mList = _reply.createTypedArrayList(Person.CREATOR);
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            _data.recycle();
            _reply.recycle();
        }
        return mList;
    }
}
