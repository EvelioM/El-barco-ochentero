package com.evelio.elbarcoochentero.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.evelio.elbarcoochentero.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private View.OnTouchListener onTouchNew;
    private ScrollView scroll;
    private static boolean done = false;

    public FirstFragment(View.OnTouchListener onTouch){
        this.onTouchNew = onTouch;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        scroll = view.findViewById(R.id.fragment_one);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onTouchNew.onTouch(v,event);
                if(!done){
                    done = true;
                    scroll.dispatchTouchEvent(event);
                }
                done = false;
                return true;
            }
        });
        return view;
    }
}
