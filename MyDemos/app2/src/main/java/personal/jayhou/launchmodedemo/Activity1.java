package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Activity1 extends Activity {

    private static final String TAG = "Activity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate " + this);
        setContentView(R.layout.activity_1);
        Utils.initAllWidget(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.LogAndTost(this,TAG, "onDestroy");
    }
}
