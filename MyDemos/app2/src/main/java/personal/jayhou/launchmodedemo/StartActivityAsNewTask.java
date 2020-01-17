package personal.jayhou.launchmodedemo;

import android.app.Activity;
import android.os.Bundle;

public class StartActivityAsNewTask extends Activity {
    private static final String TAG = "StartActivityAsNewTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_task);
        Utils.initAllWidget(this);
    }
}
