package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.os.Bundle;

public class Activity2 extends Activity {
    private static final String TAG = "Activity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Utils.initAllWidget(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.LogAndTost(this,TAG, "onDestroy");
    }
}
