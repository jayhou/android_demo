package com.jayhou.demo.onewaytrapdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    private static final String TAG = "HDEMO-RemoteService";

    private BinderService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new BinderService();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }

    private volatile int mCount = 0;

    private class BinderService extends IRemoteService.Stub {

        @Override
        public void remoteCallbackTest() throws RemoteException {
            Log.d(TAG, "remoteCallbackTest before wait " + (mCount++));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "remoteCallbackTest after wait " + (--mCount));
        }
    }
}
