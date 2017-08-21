package com.example.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/18.
 */

public class Command implements Parcelable {

    private String name;
    private int id;

    public Command() {
    }

    public Command(Parcel parcelable) {
        name = parcelable.readString();
        id = parcelable.readInt();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static final Creator<Command> CREATOR = new Creator<Command>() {

        @Override
        public Command createFromParcel(Parcel source) {
            return new Command(source);
        }

        @Override
        public Command[] newArray(int size) {
            return new Command[size];
        }
    };

    @Override
    public String toString() {
        return id + name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param dest
     */
    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = dest.readString();
        id = dest.readInt();
    }



}
