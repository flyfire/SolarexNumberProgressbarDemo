package com.solarexsoft.solarexnumberprogressbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.solarexsoft.solarexnumberprogressbar.SolarexNumberProgressbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SolarexNumberProgressbar snp_5 = findViewById(R.id.snp_5);
        snp_5.setProgress(60);
    }
}
