package com.whatsapp.status.downloader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.whatsapp.status.downloader.R;
import com.whatsapp.status.downloader.fragment.TabsFragment;
import com.whatsapp.status.downloader.utils.AndroidUtils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFragment(new TabsFragment(),false,"");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AndroidUtils.shareApplicationLink(MainActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            loadFragment(new TabsFragment(),true,"");

        }
         else if (id == R.id.nav_share) {
            shareAppLink();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /***
     * Load Fragment
     * @param fragment
     * @param isMenuItem
     * @param title
     */
    public void loadFragment(Fragment fragment, boolean isMenuItem, String title) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_content, fragment, fragment.getClass().getSimpleName());
        if(isMenuItem){
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 0;i < fm.getBackStackEntryCount();i++){
                Log.d(TAG,"Back Stack Entry--->" + getFragmentManager().getBackStackEntryCount());
                //Clear Fragment Back Stack
                fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                //titleList.clear();
            }
        }
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
        //titleList.add(title);
        //toolbar.setTitle(title);
        //Log.d(TAG,"Toolbar Title -->" + toolbar.getTitle());
    }

    private void shareAppLink() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");// Plain format text

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, concatSharebleLinkData());
        startActivity(Intent.createChooser(sharingIntent, "Share Text Using"));
    }

    private String concatSharebleLinkData() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("This is a Great App, Please download from here");
        stringBuilder.append("\n");
        stringBuilder.append("http://play.google.com/store/apps/details?id=" + getPackageName());
        return stringBuilder.toString();
    }
}
