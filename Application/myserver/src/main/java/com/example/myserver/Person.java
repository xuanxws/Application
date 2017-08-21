package com.example.myclient;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/21.
 */

public class Person implements Parcelable {

    private String id;
    private String name;
    private int age;

    public Person() {
    }

    protected Person(Parcel in) {
        id = in.readString();
        name = in.readString();
        age = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(age);
    }

    public void readFromParcel(Parcel dest){
        dest.readString();
        dest.readInt();
        dest.readString();
    }
}
