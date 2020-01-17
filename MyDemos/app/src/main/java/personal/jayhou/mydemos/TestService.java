package personal.jayhou.mydemos;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import personal.jayhou.aidllib.IPermissionService;

public class TestService {
    private static final String TAG = "TestService";

    private static final String ACTION_SERVICEA = "com.jayhou.intent.action.ServiceA";
    private static final String PACKAGE_NAME_SERVICEA = "personal.jayhou.servicea";
    private static final String CLASS_NAME_SERVICEA = "personal.jayhou.servicea.ServiceA";

    private static final String ACTION_PERMISSION_SERVICE = "com.jayhou.intent.action.PERMISION_SERVICE";
    private static final String PACKAGE_NAME_PERMISSION_SERVICE = "personal.jayhou.permissionservice";
    private static final String CLASS_NAME_PERMISSION_SERVICE = "personal.jayhou.permissionservice.PermissionService";

    private static final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected cp:" + componentName);
            IPermissionService service = IPermissionService.Stub.asInterface(iBinder);
            try {
                service.testPermission();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected cp:" + componentName);
        }
    };

    public static final void bindService(Context context) {
        Intent serviceA = new Intent(ACTION_PERMISSION_SERVICE);
        ComponentName cp = new ComponentName(PACKAGE_NAME_PERMISSION_SERVICE,CLASS_NAME_PERMISSION_SERVICE);
        serviceA.setComponent(cp);
        context.bindService(serviceA, mServiceConnection,Context.BIND_AUTO_CREATE);
    }

    public static final void unbindService(Context context) {
        context.unbindService(mServiceConnection);
    }
}
