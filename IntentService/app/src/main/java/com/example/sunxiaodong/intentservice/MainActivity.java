package com.example.sunxiaodong.intentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NAME = MainActivity.class.getSimpleName();
    private static final String TAG = "sxd";

    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnbindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mStartService = (Button) this.findViewById(R.id.start_service);
        mStartService.setOnClickListener(this);
        mStopService = (Button) this.findViewById(R.id.stop_service);
        mStopService.setOnClickListener(this);
        mBindService = (Button) this.findViewById(R.id.bind_service);
        mBindService.setOnClickListener(this);
        mUnbindService = (Button) this.findViewById(R.id.unbind_service);
        mUnbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                startService();
                break;
            case R.id.stop_service:
                stopService();
                break;
            /*case R.id.bind_service:
                bindService();
                break;
            case R.id.unbind_service:
                unbindService();
                break;*/
        }
    }

    private void startService() {
        Log.i(TAG, NAME + "--startService++mainThread++id:" + Thread.currentThread().getId());
//        Intent intent = new Intent(this, MyIntentService.class);
        Intent intent = new Intent(this, ImitateIntentService.class);
        intent.putExtra(MyIntentService.INTENT_KEY, "1");
        startService(intent);
        intent.putExtra(MyIntentService.INTENT_KEY, "2");
        startService(intent);
        intent.putExtra(MyIntentService.INTENT_KEY, "3");
        startService(intent);
    }

    private void stopService() {
//        Intent intent = new Intent(this, MyIntentService.class);
        Intent intent = new Intent(this, ImitateIntentService.class);
        stopService(intent);
    }

    /*private void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void unbindService() {
        unbindService(mConnection);
    }*/

}
