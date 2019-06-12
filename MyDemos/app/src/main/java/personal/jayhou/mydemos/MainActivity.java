package personal.jayhou.mydemos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;

import personal.jayhou.mydemos.aidl.ICallback;
import personal.jayhou.mydemos.aidl.IRemoteService;

public class MainActivity extends Activity {

    private static final String TAG = "RemoteCallbackTest-Activity";

    private static final boolean REMOTE_CALL_EXECUTE_IN_MAIN_THREAD = false;

    ICallback mCallback = new ICallback.Stub() {
        @Override
        public void callback() throws RemoteException {
            Log.d(TAG, "called in " + (REMOTE_CALL_EXECUTE_IN_MAIN_THREAD?" main thread right?":" in binder thread right?"));
        }
    };

    private void registerCallbackToRemote(IRemoteService service) {
        try {
            service.callbackTrans(mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            final IRemoteService service = IRemoteService.Stub.asInterface(iBinder);
                if(REMOTE_CALL_EXECUTE_IN_MAIN_THREAD) {
                    Log.d(TAG, "called in main thread make main thread wait for binder driver return, but the main thread in idle state.");
                    registerCallbackToRemote(service);
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            Log.d(TAG, "called in other thread.");
                            registerCallbackToRemote(service);
                        }
                    }.start();
                }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent remoteService = new Intent(getApplicationContext(),RemoteService.class);
        bindService(remoteService,mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
