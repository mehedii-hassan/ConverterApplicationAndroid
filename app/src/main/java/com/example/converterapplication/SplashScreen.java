package com.example.converterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        //progressBar.setScaleY(3f);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }
        });

        thread.start();

    }

    private void doWork() {

        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(300);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {

            }

        }

    }


}