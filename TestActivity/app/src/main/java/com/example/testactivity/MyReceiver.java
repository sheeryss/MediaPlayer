package com.example.testactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "com.test.play":
                Toast.makeText(context, "play audio", Toast.LENGTH_SHORT).show();
                break;
            case "com.test.pause":
                Toast.makeText(context, "pause audio", Toast.LENGTH_SHORT).show();
                break;
            case "com.test.stop":
                Toast.makeText(context, "stop audio", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
