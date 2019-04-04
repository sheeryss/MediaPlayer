package com.example.testactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "TestActivity";
    private FragmentManager fManager;
    private MyFragment fg1, fg2, fg3, fg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        fManager=getFragmentManager();
        bindView();
    }

    public void bindView(){
        findViewById(R.id.local_mc).setOnClickListener(MainActivity.this);
        findViewById(R.id.online).setOnClickListener(MainActivity.this);
        findViewById(R.id.owner).setOnClickListener(MainActivity.this);
        findViewById(R.id.down_group).setOnClickListener(MainActivity.this);
        findViewById(R.id.ly_top_bar).setOnClickListener(MainActivity.this);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1!=null)fragmentTransaction.hide(fg1);
        if(fg2!=null)fragmentTransaction.hide(fg2);
        if(fg3!=null)fragmentTransaction.hide(fg3);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.local_mc:
                Log.d(TAG, "click fg1");
                if(fg1==null){
                    Log.d(TAG, "start fg1");
                    fg1 = new MyFragment(this);
                    fTransaction.add(R.id.ly_content, fg1);
                }else{
                    Log.d(TAG, "show fg1");
                    fTransaction.show(fg1);
                }
                break;
            case R.id.online:
                break;
            case R.id.owner:
                break;
        }
        Log.d(TAG, "commit");
        fTransaction.commit();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "destory");
        super.onDestroy();
    }
}
