package com.whatsapp.status.downloader.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.utils.AndroidUtils;

import java.io.File;

import de.mateware.snacky.Snacky;

import static com.whatsapp.status.downloader.utils.Constant.IMAGE_PATH;
import static com.whatsapp.status.downloader.utils.Constant.VIDEO_PATH;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class FullShowVideoFragment extends Fragment  implements EasyVideoCallback {

    private EasyVideoPlayer player;
    private FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_video, container, false);
        initializationView(view);

        final String value = getArguments().getString("imageUri");
        player.setCallback(this);
        player.setSource(Uri.parse(value));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Runnable() {
                    @Override
                    public void run() {
                        try {

                            //copyFile(new File(value), new File(DIRECTORY_TO_SAVE_MEDIA_NOW + new File(value).getName()));
                            AndroidUtils.copyFile(new File(value), new File(VIDEO_PATH + new File(value).getName()));
                            Snacky.builder().
                                    setActivty(getActivity()).
                                    setText(R.string.save_successful_message).
                                    success().
                                    show();//
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

    private void initializationView(View view) {
        player = (EasyVideoPlayer) view.findViewById(R.id.player);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        player.pause();
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {

    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {

    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }
}
