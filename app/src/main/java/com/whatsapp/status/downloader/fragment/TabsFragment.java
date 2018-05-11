package com.whatsapp.status.downloader.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.adapter.ViewPageAdapter;

/**
 * Created by Rid's Patel on 09-02-2018.
 */

public class TabsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        setUpView(view);
        setupTabIcons();
        setViewPageAdapter();

        return view;
    }

    /**
     * Initialize View
     * @param view
     */
    private void setUpView(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    }
    /**
     *Set Viewpager Adapter
     */
    private void setViewPageAdapter(){
        viewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    /**
     * Set Up Icons
     */
    private void setupTabIcons() {
        tabLayout.addTab(tabLayout.newTab().setText("Images"));
        tabLayout.addTab(tabLayout.newTab().setText("Videos"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }


}
