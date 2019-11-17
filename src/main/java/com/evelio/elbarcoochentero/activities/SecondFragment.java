package com.evelio.elbarcoochentero.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.evelio.elbarcoochentero.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    VideoView videoView;
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter adapter;

    private View.OnTouchListener onTouchNew;

    public SecondFragment(View.OnTouchListener onTouch){
        this.onTouchNew = onTouch;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        videoView = (VideoView) view.findViewById(R.id.your_video_view);
        listView = (ListView) view.findViewById(R.id.listView);

        view.setOnTouchListener(onTouchNew);

        videoList = new ArrayList<>();
        videoList.add("Video barco");
        videoList.add("Video barco 2");

        adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, videoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.video_barco));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.video_barco2));
                        break;
                    default:
                        break;
                }
                videoView.setMediaController(new MediaController(view.getContext()));
                videoView.requestFocus();
                videoView.start();
            }
        });

        return view;
    }
}
