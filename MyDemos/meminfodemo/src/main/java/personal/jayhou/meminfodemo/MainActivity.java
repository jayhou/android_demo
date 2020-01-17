package personal.jayhou.meminfodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private long[] m_8MB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_8MB = new long[1024*1024];
        for(int i = 0; i < 1024*1024/2; i++) {
            m_8MB[i] = 0l;
        }
    }
}
