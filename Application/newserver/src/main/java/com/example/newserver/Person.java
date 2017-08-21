package com.example.newserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/21.
 */

public class Person implements Parcelable {


    private int id;
    private String name;
    private String age;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readString();
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
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(age);
    }

    public void readFromParcel(Parcel dest){
        id = dest.readInt();
        name = dest.readString();
        age = dest.readString();
    }
}
