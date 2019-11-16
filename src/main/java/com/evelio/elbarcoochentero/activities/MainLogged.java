package com.evelio.elbarcoochentero.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.evelio.elbarcoochentero.R;
import com.google.android.material.navigation.NavigationView;


public class MainLogged extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SecondFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThirdFragment()).commit();
                break;
            case R.id.nav_settings:
                Intent intencionxml = new Intent(MainLogged.this, SettingsContainer.class);
                MainLogged.this.startActivity(intencionxml);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Compartido", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Enviado", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

