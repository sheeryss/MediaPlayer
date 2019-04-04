package com.example.testactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import static android.content.Context.BIND_AUTO_CREATE;


public class MyFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Fragment";
    private final Activity activity;
    private MyService myservice;

    public MyFragment(Activity activity){
        this.activity = activity;
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MyService.MyBinder myBinder = (MyService.MyBinder) binder;
            myservice = myBinder.getService();
            Log.d(TAG,"onBind");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("Kathy", "ActivityA - onServiceDisconnected");
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(activity.getApplicationContext(), MyService.class);
        activity.getApplicationContext().bindService(intent, conn, BIND_AUTO_CREATE);
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity.findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myservice.play();
            }
        });
        activity.findViewById(R.id.pause).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                myservice.pause();
            }
        });
        activity.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 myservice.stop();
             }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.myfragment, container, false);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play:
                Log.d(TAG,"START PALY AUDIO");
                myservice.play();
                Log.d(TAG,"PALY AUDIO");
                break;
            case R.id.pause:
                myservice.pause();
                break;
            case R.id.stop:
                myservice.stop();
                break;
        }
    }
    @Override
    public void onDestroy() {
        Log.d(TAG, "destory");
        super.onDestroy();

    }
}
