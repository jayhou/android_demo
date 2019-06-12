package personal.jayhou.mydemos;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import personal.jayhou.mydemos.aidl.ICallback;
import personal.jayhou.mydemos.aidl.IRemoteService;

public class RemoteService extends Service {
    private static final String TAG = "RemoteCallbackTest-Service";
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderService();
    }

    public static class BinderService extends IRemoteService.Stub {

        @Override
        public void callbackTrans(ICallback callback) throws RemoteException {
            if(callback != null) {
                Log.d(TAG, "call remote callback immediately.");
                callback.callback();
            }
        }
    }
}
