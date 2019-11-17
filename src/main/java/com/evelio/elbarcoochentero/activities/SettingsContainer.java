package com.evelio.elbarcoochentero.activities;

import android.os.Bundle;

import com.evelio.elbarcoochentero.R;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsContainer extends AppCompatActivity implements SettingsFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenciascontainer);


    }
}