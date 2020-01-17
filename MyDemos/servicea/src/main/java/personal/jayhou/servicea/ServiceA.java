package personal.jayhou.servicea;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseIntArray;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Random;


public class ServiceA extends Service {
    private static final String TAG = "ServiceA";
    IBinder mBinderService;
    Thread mCpuEater;
    SparseIntArray mIntArray1 = new SparseIntArray();
    SparseIntArray mIntArray2 = new SparseIntArray();

    Random mRandom = new Random(System.currentTimeMillis());

    int[] mRandomKey1 = new int[1000];
    int[] mRandomKey2 = new int[1000];

    static int value1;
    static int value2;

    @Override
    public IBinder onBind(Intent intent) {
        if(mBinderService == null) {
            mBinderService = new Binder();
        }
        return mBinderService;
    }

    private void prepare() {
        for(int i=0 ;i < 1000; i++) {
            int key1 = mRandom.nextInt();
            mIntArray1.put(key1, i);
            mRandomKey1[i] = key1;
            int key2 = mRandom.nextInt();
            mIntArray2.put(key2, i);
            mRandomKey2[i] = key2;
        }
    }

    private void hotSpot() {
//        String testString = "Random access SparseIntArray Test";
//        Log.d(TAG,testString);
        for(int i = 0; i < 20; i++) {
            int key1 = mRandomKey1[i];
            value1 = mIntArray1.get(key1);
//            Log.d(TAG, "Random key:" + key1 + " value:" + mIntArray1.get(key1));
            int key2 = mRandomKey2[i];
            value2 = mIntArray2.get(key2);
//            Log.d(TAG, "Random key:" + key2 + " value:" + mIntArray1.get(key1));
        }
    }

    private boolean mExit = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        prepare();
        mCpuEater = new Thread( new Runnable() {
            @Override
            public void run() {
                while(!mExit) {
                    hotSpot();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"eat_cpu");
        mCpuEater.start();

    }

    public ServiceA() {
        super();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mExit = true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "onTaskRemoved");
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }
}
