package com.azizulhakim.gifsplashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        //Navigation drawer menu..............................

        //Hide or show menu.................................
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_cycle:
                Intent cycle_intent = new Intent(getApplicationContext(), CycleActivity.class);
                startActivity(cycle_intent);
                break;

            case R.id.nav_bus:
                Intent bus_intent = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(bus_intent);
                break;

            case R.id.nav_plane:
                Intent plane_intent = new Intent(getApplicationContext(), AirPlaneActivity.class);
                startActivity(plane_intent);
                break;

            case R.id.nav_login:
                Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login_intent);
                break;

            case R.id.nav_logout:
                Intent logout_intent = new Intent(getApplicationContext(), LogoutActivity.class);
                startActivity(logout_intent);
                break;

            case R.id.nav_profile:
                Intent profile_intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile_intent);
                break;


            case R.id.nav_share:
                Intent share_intent = new Intent(getApplicationContext(), ShareActivity.class);
                startActivity(share_intent);
                break;

            case R.id.nav_rate:
                Intent rate_intent = new Intent(getApplicationContext(), RateActivity.class);
                startActivity(rate_intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}