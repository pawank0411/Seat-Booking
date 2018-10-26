package com.university.soa.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
       // progressBar.setMax(100);
        //progressBar.setProgress(50);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }


            private void doWork() {
                for (int progress = 0; progress < 110; progress += 10) {
                    try {
                        Thread.sleep(1000);
                        progressBar.setProgress(progress);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SplashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

}