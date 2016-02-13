package com.example.sunxiaodong.intentservice;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sunxiaodong on 16/2/13.
 */
public class ImitateIntentService extends Service {

    private static final String NAME = ImitateIntentService.class.getSimpleName();
    private static final String TAG = "sxd";

    public static final String INTENT_KEY = "intent_key";

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Log.i(TAG, NAME + "--ServiceHandler++begin:" + bundle.getString(INTENT_KEY));
            Log.i(TAG, NAME + "--ServiceHandler++currentThread++id:" + Thread.currentThread().getId());
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            Log.i(TAG, NAME + "--ServiceHandler++end:" + bundle.getString(INTENT_KEY));
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, NAME + "--onCreate");
        Log.i(TAG, NAME + "--onCreate++currentThread++id:" + Thread.currentThread().getId());
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread(NAME, Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, NAME + "--onStartCommand");
        Log.i(TAG, NAME + "--onStartCommand++currentThread++id:" + Thread.currentThread().getId());

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_KEY, intent.getStringExtra(INTENT_KEY));
        msg.setData(bundle);
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, NAME + "--onBind");
        Log.i(TAG, NAME + "--onBind++currentThread++id:" + Thread.currentThread().getId());
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, NAME + "--onDestroy");
        Log.i(TAG, NAME + "--onDestroy++currentThread++id:" + Thread.currentThread().getId());
    }
}