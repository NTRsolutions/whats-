package com.whatsapp.status.downloader.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.activity.MainActivity;
import com.whatsapp.status.downloader.adapter.ImageAdapterGridView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class VideoFragment extends Fragment {

    private static final String WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses";

    private GridView gridViewVideoFrg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        initializationView(view);

        setAdapter();

        gridViewVideoFrg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                File selectedImage =  getListFiles(new File(Environment.getExternalStorageDirectory().toString()+WHATSAPP_STATUSES_LOCATION)).get(position);
                ///Toast.makeText(getActivity(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("imageUri",selectedImage.getPath());
                FullShowVideoFragment fullShowVideoFragment = new FullShowVideoFragment();
                fullShowVideoFragment.setArguments(bundle);
                ((MainActivity)getContext()).loadFragment(fullShowVideoFragment,false,"Task Info");
            }
        });


        return view;
    }


    /**
     * Initialization View
     * @param view
     */
    private void initializationView(View view) {
        gridViewVideoFrg = (GridView) view.findViewById(R.id.gridViewVideoFrg);
    }


    private void setAdapter() {
        gridViewVideoFrg.setAdapter(new ImageAdapterGridView(getActivity(),
                getListFiles(new File(Environment.getExternalStorageDirectory().toString()+WHATSAPP_STATUSES_LOCATION))));
    }



    /**
     * get all the files in specified directory
     *
     * @param parentDir
     * @return
     */
    private ArrayList<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files;
        files = parentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".mp4")) {
                    if (!inFiles.contains(file))
                        inFiles.add(file);
                }
            }
        }
        return inFiles;
    }


}
