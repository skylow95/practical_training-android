package com.example.jordan.apitest.info_activity.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.jordan.apitest.R;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view.AbsenceInfoPersonFragment;
import com.example.jordan.apitest.info_activity.view.add_absence_person.view.AddAbsenceFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.nvView)
    NavigationView nvDrawer;

    public ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Fragment fragment = AbsenceInfoPersonFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        mDrawerToggle = setupDrawerToggle();

        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        android.support.v4.app.Fragment fragment = null;


        switch (menuItem.getItemId()) {
            case R.id.infoAbsencePerson:
                fragment = AbsenceInfoPersonFragment.newInstance();
                break;
            case R.id.addAbsence:
                fragment = AddAbsenceFragment.newInstance();
                break;
            case R.id.logout:
                this.finish();
                break;
            default:
                fragment = AbsenceInfoPersonFragment.newInstance();
        }


        // Insert the fragment by replacing any existing fragment
        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


