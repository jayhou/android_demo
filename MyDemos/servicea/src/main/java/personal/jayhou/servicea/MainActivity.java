package personal.jayhou.servicea;

import android.content.ComponentName;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Intent mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = new Intent();
        ComponentName cp = new ComponentName(this, ServiceA.class);
        mService.setComponent(cp);
        startService(mService);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mService);
    }
}
