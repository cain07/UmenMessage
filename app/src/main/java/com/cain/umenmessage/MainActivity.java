package com.cain.umenmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.umeng.message.PushAgent;

public class MainActivity extends AppCompatActivity {

    private PushAgent mPushAgent;
    private TextView applog;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyApp.UPDATE_STATUS_ACTION);
        registerReceiver(myReceiver, filter);

        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
    }

    private static final String TAG = MainActivity.class.getName();

    class MyReceiver extends BroadcastReceiver {



        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("___________________", mPushAgent.getRegistrationId());
        }
    }
}
