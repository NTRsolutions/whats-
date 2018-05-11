package com.whatsapp.status.downloader.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.whatsapp.status.downloader.fragment.ImageFragment;
import com.whatsapp.status.downloader.fragment.VideoFragment;


/**
 * Created by Rahul Padaliya on 9/27/2016.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : return new ImageFragment();
            case 1 : return new VideoFragment();

           }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Images";
            case 1 :
                return "Videos";
           }
        return null;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {}

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;  // This will get invoke as soon as you call notifyDataSetChanged on viewPagerAdapter.
    }
}
