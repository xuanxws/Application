package com.example.myclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivity mContext;
    EditText etId;
    EditText etName;
    EditText etAge;
    Button btnAdd;
    Button btnGet;
    RecyclerView mRecyclerView;
    ArrayList<Person> mList = new ArrayList<>();

    String name = "";
    String id = "";
    String age = "";

    boolean isBind = false;

    MyProXy binder;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = MyProXy.getInstance(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {
        etId = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnGet = (Button) findViewById(R.id.btn_get);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        final MyAdapter myAdapter = new MyAdapter(mContext);
        mRecyclerView.setAdapter(myAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                id = etId.getText().toString();
                age = etAge.getText().toString();
                if (checkData()) {
                    Person person = new Person();
                    person.setName(name);
                    person.setId(Integer.valueOf(id));
                    person.setAge(age);
                    if (null == binder) {
                        Log.i("test", "333");
                    }
                    binder.addPerson(person);
                    clearPerson();
                    Toast.makeText(mContext,"添加英雄成功，点击获取任务可查看英雄列表",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != binder) {
                    mList = (ArrayList<Person>) binder.getPersonList();
                }
                if (null != mList && mList.size() > 0) {
                    Log.i("test","size---" + mList.size());
                    myAdapter.addList(mList);
                } else {
                    Toast.makeText(mContext,"还没有添加英雄，赶紧添加一个吧~",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = new Intent();
        intent.setPackage("com.example.myserver");
        intent.setAction("com.example.myserver.aidl.IMyAidlInterface");
        bindService(intent,mConnection,mContext.BIND_AUTO_CREATE);
    }

    private boolean checkData() {
        if ("".equals(name)) {
            Toast.makeText(mContext, "请输入姓名~", Toast.LENGTH_SHORT).show();
            return false;
        }

        if ("".equals(id)) {
            Toast.makeText(mContext, "请输入编号~", Toast.LENGTH_SHORT).show();
            return false;
        }

        if ("".equals(age)) {
            Toast.makeText(mContext, "请输入Q龄~", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearPerson(){
        etId.setText("");
        etName.setText("");
        etAge.setText("");
    }
}
