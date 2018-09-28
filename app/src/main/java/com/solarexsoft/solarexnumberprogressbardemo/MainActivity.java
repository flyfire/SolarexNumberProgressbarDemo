package com.solarexsoft.solarexnumberprogressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.solarexsoft.solarexnumberprogressbar.SolarexNumberProgressbar;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_UPDATE = 100;
    private Handler mHandler;
    private boolean isIncrease = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SolarexNumberProgressbar snp_5 = findViewById(R.id.snp_5);
        final SolarexNumberProgressbar snp_1 = findViewById(R.id.snp_1);
        snp_5.setProgress(60);
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                if (what == MSG_UPDATE) {
                    int progress = snp_1.getProgress();
                    if (progress <= 0) {
                        progress = 0;
                        isIncrease = true;
                    } else if (progress >= 100) {
                        progress = 100;
                        isIncrease = false;
                    }
                    if (isIncrease) {
                        progress++;
                    } else {
                        progress--;
                    }
                    snp_1.setProgress(progress);
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE, 100);
                }
            }
        };
        mHandler.sendEmptyMessage(MSG_UPDATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
