package com.example.testactivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import android.util.Log;
import android.media.MediaPlayer;

public class MyService extends Service {

    private boolean isplay = false;
    private boolean ispause = false;
    private String TAG="MyService";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String filepath = "/data/wuhuan.mp3";

    /*create binder and get this service instance*/
    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
    @Override
    public void onCreate(){
        try {
            //mediaPlayer.setDataSource(filepath);
            //mediaPlayer =MediaPlayer.create(this, R.raw.wuhuan);
            //mediaPlayer.prepare();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*return binder*/
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Log.d(TAG, "onstartcommend");
        Toast.makeText(this, "Service Started adn register broadcast", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (isplay&ispause){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        Log.d(TAG, "onDestory");
        super.onDestroy();
    }

    public void play(){
        try{
            if(!isplay)
                mediaPlayer.reset();
                mediaPlayer =MediaPlayer.create(this, R.raw.wuhuan);
                mediaPlayer.start();
                isplay=true;
            Log.d(TAG, "is playing");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("err",e.getMessage());
        }
    }
    public void pause(){
        if(ispause){
            mediaPlayer.start();
            ispause=false;
        }
        else{
            mediaPlayer.pause();
            ispause=true;
            Log.d(TAG, "pause audio");
        }
    }
    public void stop(){
        if(isplay || ispause){
            mediaPlayer.stop();
            isplay=false;
            ispause=false;
            Log.d(TAG, "stop audio");
        }
    }
}
