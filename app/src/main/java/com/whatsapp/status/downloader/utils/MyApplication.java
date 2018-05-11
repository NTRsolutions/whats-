package com.whatsapp.status.downloader.utils;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.whatsapp.status.downloader.R;

/**
 * Created by Rid's Patel on 26-02-2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}
