package com.jayhou.demo.onewaytrapdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class RemoteService2 extends Service {
    private static final String TAG = "HDEMO-RemoteService2";

    private IRemoteService mRemoteService;

    private volatile int mCallCount = 0;

    private Runnable mRemoteCall = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(TAG, "RemoteService2 client call remote " + (mCallCount++));
                mRemoteService.remoteCallbackTest();
                Log.d(TAG, "RemoteService2 client call remote end " + (--mCallCount));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected :" + componentName);
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
            for(int i = 0; i < 5000; i++) {
                new Thread(mRemoteCall).start();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent();
        intent.setPackage("com.jayhou.demo.onewaytrapdemo");
        intent.setAction("action.test.remote.call");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
