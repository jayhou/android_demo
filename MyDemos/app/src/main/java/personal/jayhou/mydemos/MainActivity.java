package personal.jayhou.mydemos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
//    private TestRemoteService mTestRemoteService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startApp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent app2Activity1 = new Intent("com.jayhou.intent.action.Activity1");
                startActivity(app2Activity1);
            }
        });
//        mTestRemoteService.testRemoteService(getApplicationContext());
        //TestService.bindService(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TestService.unbindService(this);
    }


}
