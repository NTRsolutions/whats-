package com.whatsapp.status.downloader.utils;

import android.os.Environment;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class Constant {
    public static final String APP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SUB_DIR = "/We/";
    public static final String IMAGES_DIR = "images/";
    public static final String VIDEO_DIR = "videos/";
    public static final String IMAGE_PATH = APP_DIR + SUB_DIR + IMAGES_DIR;
    public static final String VIDEO_PATH = APP_DIR + SUB_DIR + VIDEO_DIR;

}
