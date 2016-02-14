package com.example.sunxiaodong.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sunxiaodong.remoteservice.IMyServiceAIDL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NAME = MainActivity.class.getSimpleName();
    private static final String TAG = "sxd";

    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnbindService;

    private EditText mAddendOne;
    private EditText mAddendTwo;
    private TextView mResult;

    private IMyServiceAIDL mIMyServiceAIDL;

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
        mAddendOne = (EditText) this.findViewById(R.id.addend_one);
        mAddendTwo = (EditText) this.findViewById(R.id.addend_two);
        mResult = (TextView) this.findViewById(R.id.result);
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
            case R.id.bind_service:
                bindService();
                break;
            case R.id.unbind_service:
                unbindService();
                break;
        }
    }

    private void startService() {
        Intent intent = new Intent("com.example.sunxiaodong.remoteservice.MyService");
        intent.setPackage("com.example.sunxiaodong.remoteservice");//Android5.0后Service不能采用隐式启动，所以必须加上包名
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent("com.example.sunxiaodong.remoteservice.MyService");
        intent.setPackage("com.example.sunxiaodong.remoteservice");//Android5.0后Service不能采用隐式启动，所以必须加上包名
        stopService(intent);
    }

    private void bindService() {
        Intent intent = new Intent("com.example.sunxiaodong.remoteservice.MyService");
        intent.setPackage("com.example.sunxiaodong.remoteservice");//Android5.0后Service不能采用隐式启动，所以必须加上包名
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void unbindService() {
        unbindService(mConnection);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyServiceAIDL = IMyServiceAIDL.Stub.asInterface(service);
            try {
                int result = mIMyServiceAIDL.add(Integer.parseInt(mAddendOne.getText().toString()), Integer.parseInt(mAddendTwo.getText().toString()));
                mResult.setText(String.valueOf(result));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

}
