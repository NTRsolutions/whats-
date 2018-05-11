package com.whatsapp.status.downloader.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.whatsapp.status.downloader.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class AndroidUtils {

    /**
     * copy file to destination.
     *
     * @param sourceFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public static void shareApplicationLink(Activity context) {

        //if(MarshMallowPermission.checkPermissionForWriteExternalStorage(context)) {
            String SHARING_TEXT = "Let me recommend you this application\n: https://play.google.com/store/apps/details?id=" + context.getPackageName();
            Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_logo);
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            //sharingIntent.setType("image/jpeg/text/plain");
            sharingIntent.setDataAndType(Uri.parse("file:///sdcard/mobicast.jpg"), "image/jpeg/text/plain");
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            largeIcon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "mobicast.jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

            sharingIntent.putExtra("sms_body", SHARING_TEXT);
            sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/mobicast.jpg"));
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, SHARING_TEXT);
            sharingIntent.putExtra(Intent.ACTION_ATTACH_DATA, SHARING_TEXT);

            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } /*else {
            MarshMallowPermission.requestPermissionForWriteExternalStorage(context);
        }
    }*/


}
