package personal.jayhou.permissionservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;

import personal.jayhou.aidllib.IPermissionService;

public class PermissionService extends Service {

    private final static String TAG = "PermissionService";

    private final static String TEST_PERMISSION = "jayhou.permission.test";

    private final static String TEST_PERMISSION_TREE = "jayhou.permission.tree_test";
    private final static String TREE_NORMAL_PER = ".treeNormal";
    private final static String TREE_SIGNATURE_PER = ".treeSignature";
    private final static String TEST_PERMISSION_TREE_NORMAL = TEST_PERMISSION_TREE + TREE_NORMAL_PER;
    private final static String TEST_PERMISSION_TREE_SIGNATURE = TEST_PERMISSION_TREE + TREE_SIGNATURE_PER;

    private BinderService mPermisiionService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
        addDynamicPermissions();
        mPermisiionService = new BinderService();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mPermisiionService;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    class BinderService extends IPermissionService.Stub {

        @Override
        public void testPermission() throws RemoteException {
            Log.d(TAG, "testPermission");
            if( PackageManager.PERMISSION_GRANTED == checkCallingPermission(TEST_PERMISSION)) {
                logPermissionStatus(TEST_PERMISSION, PackageManager.PERMISSION_GRANTED);
            } else {
                logPermissionStatus(TEST_PERMISSION, PackageManager.PERMISSION_DENIED);
                throw new SecurityException("require permission: " + TEST_PERMISSION);
            }

//            if(PackageManager.PERMISSION_GRANTED == checkCallingPermission(TEST_PERMISSION_TREE_NORMAL)) {
//                logPermissionStatus(TEST_PERMISSION_TREE_NORMAL, PackageManager.PERMISSION_GRANTED);
//            } else {
//                logPermissionStatus(TEST_PERMISSION_TREE_NORMAL, PackageManager.PERMISSION_DENIED);
//                throw new SecurityException("require permission: " + TEST_PERMISSION_TREE_NORMAL);
//            }
//
//            if(PackageManager.PERMISSION_GRANTED == checkCallingPermission(TEST_PERMISSION_TREE_SIGNATURE)) {
//                logPermissionStatus(TEST_PERMISSION_TREE_SIGNATURE, PackageManager.PERMISSION_GRANTED);
//            } else {
//                logPermissionStatus(TEST_PERMISSION_TREE_SIGNATURE, PackageManager.PERMISSION_DENIED);
//                throw new SecurityException("require permission: " + TEST_PERMISSION_TREE_SIGNATURE);
//            }
        }
    }

    private void logPermissionStatus(String permission, int status) {
        Log.d(TAG, permission + (status==PackageManager.PERMISSION_GRANTED?" >>>> Permission granted!":" <<<< Permission denied!"));
    }

    private void addDynamicPermissions() {
//        PackageManager pm = getPackageManager();
//        PermissionInfo testDynamicPermissionInfoNormal = new PermissionInfo();
//        testDynamicPermissionInfoNormal.name = TEST_PERMISSION_TREE_NORMAL ;
//        testDynamicPermissionInfoNormal.labelRes = R.string.app_name;
//        testDynamicPermissionInfoNormal.protectionLevel = PermissionInfo.PROTECTION_NORMAL;
//        pm.addPermission(testDynamicPermissionInfoNormal);

//        PermissionInfo testDynamicPermissionInfoSignature = new PermissionInfo();
//        testDynamicPermissionInfoSignature.name = TEST_PERMISSION_TREE_SIGNATURE;
//        testDynamicPermissionInfoSignature.labelRes = R.string.app_name;
//        testDynamicPermissionInfoSignature.protectionLevel = PermissionInfo.PROTECTION_SIGNATURE;
//        pm.addPermission(testDynamicPermissionInfoSignature);

    }
}
