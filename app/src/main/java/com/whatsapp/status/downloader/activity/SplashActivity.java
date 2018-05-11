package com.whatsapp.status.downloader.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whatsapp.status.downloader.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        handler();
    }

    private void handler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Check User Ready Login Or Not
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                finish();
                startActivity(intent);

            }
        }, 5000);
    }
}
