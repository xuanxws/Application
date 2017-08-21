package com.example.myserver;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.myclient.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public abstract class Stub extends Binder {

    public static final int ADD = 0;
    public static final int GET = 1;

    public abstract void addPerson(Person person);
    public abstract List<Person> getPerson();

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code){
            case ADD:
                Person person;
                if (0 != data.readInt()) {
                    person = Person.CREATOR.createFromParcel(data);
                } else {
                    person = null;
                }
                this.addPerson(person);
                reply.writeInt(1);
                reply.writeNoException();
                return true;
            case GET:
                List<Person> mList;
                mList = this.getPerson();
                reply.writeNoException();
                reply.writeTypedList(mList);
                return true;
            default:
                return super.onTransact(code, data, reply, flags);
        }
    }
}
