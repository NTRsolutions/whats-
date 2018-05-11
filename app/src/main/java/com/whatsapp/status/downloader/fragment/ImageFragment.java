package com.whatsapp.status.downloader.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.activity.MainActivity;
import com.whatsapp.status.downloader.adapter.ImageAdapterGridView;
import com.whatsapp.status.downloader.adapter.RecyclerViewMediaAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Rid's Patel on 08-02-2018.
 */

public class ImageFragment extends Fragment {

    private static final String WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses";
    private RecyclerView mRecyclerViewMediaList;
    private RecyclerViewMediaAdapter recyclerViewMediaAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    public static final String TAG = "Home";
    private GridView gridViewImageFrg;
    private AdView mAdView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_grid, container, false);

        initializationView(view);
        setAdapter();
        initializationAdMob();

        gridViewImageFrg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
               File selectedImage =  getListFiles(new File(Environment.getExternalStorageDirectory().toString()+WHATSAPP_STATUSES_LOCATION)).get(position);
                //Toast.makeText(getActivity(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("imageUri",selectedImage.getPath());
                FullShowImageFragment fullShowImageFragment = new FullShowImageFragment();
                fullShowImageFragment.setArguments(bundle);
                ((MainActivity)getContext()).loadFragment(fullShowImageFragment,false,"Task Info");
            }
        });





        return view;
    }

    /**
     * InitializationView
     */
    private void initializationView(View view) {
        mRecyclerViewMediaList = (RecyclerView) view.findViewById(R.id.recyclerViewMedia);
        gridViewImageFrg = (GridView) view.findViewById(R.id.gridViewImageFrg);
        mAdView = (AdView) view.findViewById(R.id.adView);
    }

    private void initializationAdMob() {
        //mAdView.setAdSize(AdSize.BANNER);
       // mAdView.setAdUnitId(getString(R.string.banner_home_footer_unit_id));

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                 .addTestDevice("F4EEB08728A09AD951329AA89D7AF8B2")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

    private void setAdapter() {
       /* mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewMediaList.setLayoutManager(mLinearLayoutManager);
        System.out.println("......"+ Environment.getExternalStorageDirectory().toString());
        recyclerViewMediaAdapter = new RecyclerViewMediaAdapter(this.getListFiles(new File(Environment.getExternalStorageDirectory().toString()+WHATSAPP_STATUSES_LOCATION)),

                getActivity());
        mRecyclerViewMediaList.setAdapter(recyclerViewMediaAdapter);
*/
        gridViewImageFrg.setAdapter(new ImageAdapterGridView(getActivity(),
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

                /*if (file.getName().endsWith(".jpg") ||
                        file.getName().endsWith(".gif") ||
                        file.getName().endsWith(".mp4")) {
                    if (!inFiles.contains(file))
                        inFiles.add(file);
                }*/
                if (file.getName().endsWith(".jpg")) {
                    if (!inFiles.contains(file))
                        inFiles.add(file);
                }
            }
        }
        return inFiles;
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
