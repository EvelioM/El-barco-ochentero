package com.evelio.elbarcoochentero.activities;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.evelio.elbarcoochentero.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class MainLogged extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private TextView nick;
    private TextView email;
    private String nickStr;
    private View cont;
    private View.OnTouchListener onTouch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation);
        View hView = navigationView.getHeaderView(0);
        nick = hView.findViewById(R.id.nick);
        email = hView.findViewById(R.id.email);

        Intent intent = getIntent();
        nickStr = intent.getStringExtra("nick");
        nick.setText(nickStr);
        email.setText(nickStr + "@barco.com");

        cont = findViewById(R.id.fragment_container);

        this.onTouch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int historySize = event.getHistorySize();
                    if (historySize > 0 && event.getHistoricalX(historySize - 1) > event.getHistoricalX(0)) {
                        if (!drawer.isDrawerOpen(GravityCompat.START)) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    }
                }

                return true;
            }
        };
        cont.setOnTouchListener(onTouch);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment(onTouch)).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
        contextOfApplication = getApplicationContext();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment(onTouch)).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SecondFragment(onTouch)).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThirdFragment(this)).commit();
                break;
            case R.id.nav_settings:
                Intent intencionxml = new Intent(MainLogged.this, SettingsContainer.class);
                MainLogged.this.startActivity(intencionxml);
                break;
            case R.id.nav_game:
                Intent intent2 = new Intent(this, GameActivity.class);
                startActivityForResult(intent2, 5555);
                break;
            case R.id.nav_picture:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5555 && resultCode == RESULT_OK) {
            String resultado = data.getExtras().getString("resultado");
            if (resultado.equals("WIN")) {
                nick.setText(nickStr + " el ganador");
                Notification notification = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_win)
                        .setContentTitle("Has ganado")
                        .setContentText("Eres el mejor")
                        .build();
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, notification);
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            } else if (resultado.equals("LOSE")) {
                nick.setText(nickStr + " el perdedor");
                Notification notification = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_close_black_24dp)
                        .setContentTitle("Has perdido")
                        .setContentText("Eres un parguelas")
                        .build();
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, notification);
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        }
    }

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

}

