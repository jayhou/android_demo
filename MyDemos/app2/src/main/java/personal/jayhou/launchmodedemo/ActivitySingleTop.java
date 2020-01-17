package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivitySingleTop extends Activity {
    private static final String TAG = "ActivitySingleTop";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        Utils.initAllWidget(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Utils.LogAndTost(this, TAG, "onNewIntent");
    }
}
