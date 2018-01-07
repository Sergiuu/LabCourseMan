package com.sergiu.labcourse;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    //  Declarations
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.main_drawer);
        actionBarDrawerToggle =  new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment() );
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_menu:
                        fragment = new HomeFragment();
                        break;
                    case R.id.dashboard_menu:
                        fragment = new DashboardFragment();
                        break;
                }
                if( fragment!= null ){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.commit();
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater   inflater = getMenuInflater();
        inflater.inflate(R.menu.t_oolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}

