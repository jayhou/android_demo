package com.jayhou.demo.onewaytrapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HDEMO_MainActivity";

    private Button mTestButton;
    private IRemoteService mRemoteService;

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mRemoteService != null) {
                try {
                    Log.d(TAG, "before remoteCallbackTest");
                    mRemoteService.remoteCallbackTest();
                    Log.d(TAG, "after remoteCallbackTest");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected :" + componentName);
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
            mTestButton.setClickable(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Intent intent2 = new Intent();
        intent2.setPackage("com.jayhou.demo.onewaytrapdemo");
        intent2.setAction("action.test.remote.call2");
        startService(intent2);
        Intent intent = new Intent();
        intent.setPackage("com.jayhou.demo.onewaytrapdemo");
        intent.setAction("action.test.remote.call");
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        mTestButton = findViewById(R.id.click);
        mTestButton.setOnClickListener(mClick);
    }
}
