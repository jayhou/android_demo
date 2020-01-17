package personal.jayhou.mydemos;

import android.app.Activity;
import android.os.Bundle;

/**
 * 主要的demo Activity
 */

public class MainActivity extends Activity {
    private TestRemoteService mTestRemoteService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Const.TEST_REMOTE_CALL_IN_MAIN_THREAD) {
            mTestRemoteService = new TestRemoteService();
            mTestRemoteService.testRemoteService(getApplicationContext());
        }
//        TestService.bindService(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        TestService.unbindService(this);
    }


}
