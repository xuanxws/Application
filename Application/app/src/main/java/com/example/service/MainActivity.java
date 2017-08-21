package com.example.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.service.application.R;


public class MainActivity extends AppCompatActivity {

    Button mButton;

    IRemoteInterface mInterface;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            String string = null;
           mInterface = IRemoteInterface.Stub.asInterface(service);
            try {
                string = mInterface.getCommand().getName() + mInterface.getCommand().getId();
                if (null != string) {
                    Log.i("test", "command:" + string);
                } else {
                    Log.i("test","error!!!!!");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.service.aidl.IRemoteInterface");
                intent.setPackage("com.example.service");
                bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }
}
