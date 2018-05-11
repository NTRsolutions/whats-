package com.whatsapp.status.downloader.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.activity.MainActivity;
import com.whatsapp.status.downloader.utils.AndroidUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import de.mateware.snacky.Snacky;

import static com.whatsapp.status.downloader.utils.Constant.IMAGE_PATH;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class FullShowImageFragment extends Fragment {


    private static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/storage/emulated/legacy/WSDownloader/";
    ImageView ivFullImage;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fullshowimage, container, false);
        initializationView(view);
        final String value = getArguments().getString("imageUri");

        Glide.with(getContext()).load(Uri.fromFile(new File(value)))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivFullImage);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //String DIRECTORY_TO_SAVE_MEDIA_NOW = "/storage/emulated/legacy/"+ Resources.getSystem().getString(R.string.app_name)+"/";

                            Log.d("Hello", "onClick:  "+DIRECTORY_TO_SAVE_MEDIA_NOW);

                            //copyFile(new File(value), new File(DIRECTORY_TO_SAVE_MEDIA_NOW + new File(value).getName()));
                            AndroidUtils.copyFile(new File(value), new File(IMAGE_PATH + new File(value).getName()));
                            Snacky.builder().
                                    setActivty(getActivity()).
                                    setText(R.string.save_successful_message).
                                    success().
                                    show();
                           // getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                            //Toast.makeText(activity,R.string.save_successful_message,Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("RecyclerV", "onClick: Error:"+e.getMessage() );

                            Snacky.builder().
                                    setActivty(getActivity()).
                                    setText(R.string.save_error_message).
                                    error().
                                    show();
                            //Toast.makeText(activity,R.string.save_error_message,Toast.LENGTH_SHORT).show();
                        }
                    }

                }.run();
            }
        });

        return view;
    }

    /**
     * Initialization View
     * @param view
     */
    private void initializationView(View view) {
        ivFullImage = (ImageView) view.findViewById(R.id.ivFullImage);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }



}
