package com.whatsapp.status.downloader.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class ImageAdapterGridView extends BaseAdapter {
    private Context mContext;
    private static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/storage/emulated/legacy/WSDownloader/";
    private ArrayList<File> filesList;
    private Activity activity;

    public ImageAdapterGridView(Context c,ArrayList<File> filesList) {
        mContext = c;
        this.filesList = filesList;
    }

    public int getCount() {
        return filesList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        File currentFile = filesList.get(position);
        ImageView mImageView;


        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(5, 5, 5, 5);

        } else {
            mImageView = (ImageView) convertView;
        }
        //Bitmap myBitmap = BitmapFactory.decodeFile(currentFile.getAbsolutePath());
        //mImageView.setImageBitmap(myBitmap);
        Glide.with(mContext).load(Uri.fromFile(currentFile)).asBitmap()
                //.thumbnail(0.5f)
                ///.crossFade()
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);

        return mImageView;
    }
}


