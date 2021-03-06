package com.example.sunxiaodong.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sunxiaodong on 2016/1/29.
 */
public class MyService extends Service {

    private static final String NAME = MyService.class.getSimpleName();
    private static final String TAG = "sxd";

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        //该Service被首次创建时调用
        Log.i(TAG, NAME + "--onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, NAME + "--onStartCommand");
        //该Service正在运行，client再次调用startService()时调用
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, NAME + "--onBind");
        //client调用bindService()绑定到该Service时调用
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, NAME + "--onUnbind");
        //所有已绑定到该Service的client调用unbindService()与该Service解绑时调用
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, NAME + "--onRebind");
        //当onUnbind()被调用后，又有新的client调用bindService()来绑定该Service时调用
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, NAME + "--onDestroy");
        //该Service不再使用，销毁时调用
        super.onDestroy();
    }

    class MyBinder extends Binder {

        public void doSomething() {
            Log.i(TAG, NAME + "--MyBinder++doSomething()");
        }

    }

}
