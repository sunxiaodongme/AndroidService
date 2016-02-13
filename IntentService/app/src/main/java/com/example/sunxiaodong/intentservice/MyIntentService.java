package com.example.sunxiaodong.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sunxiaodong on 16/2/12.
 */
public class MyIntentService extends IntentService {

    private static final String NAME = MyIntentService.class.getSimpleName();
    private static final String TAG = "sxd";

    public static final String INTENT_KEY = "intent_key";

    public MyIntentService() {
        super(NAME);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, NAME + "--onCreate");
        Log.i(TAG, NAME + "--onCreate++currentThread++id:" + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, NAME + "--onStartCommand");
        Log.i(TAG, NAME + "--onStartCommand++currentThread++id:" + Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, NAME + "--onHandleIntent++begin:" + intent.getStringExtra(INTENT_KEY));
        Log.i(TAG, NAME + "--onHandleIntent++currentThread++id:" + Thread.currentThread().getId());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, NAME + "--onHandleIntent++end:" + intent.getStringExtra(INTENT_KEY));
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, NAME + "--onDestroy");
        Log.i(TAG, NAME + "--onDestroy++currentThread++id:" + Thread.currentThread().getId());
        super.onDestroy();
    }

}
