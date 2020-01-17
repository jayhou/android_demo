package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ActivitySingleTask extends Activity {
    private static final String TAG = "ActivitySingleTask";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_single_task);
        Utils.initAllWidget(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Utils.LogAndTost(this, TAG, "onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.LogAndTost(this,TAG, "onDestroy");
    }
}
