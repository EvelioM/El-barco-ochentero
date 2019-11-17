package com.evelio.elbarcoochentero.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.evelio.elbarcoochentero.R;

import java.util.ArrayList;

public class SecondFragment extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_two);

        videoView = (VideoView) findViewById(R.id.your_video_view);
        listView = (ListView) findViewById(R.id.listView);

        videoList = new ArrayList<>();
        videoList.add("Video barco");
        videoList.add("Video barco 2");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, videoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_barco));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_barco2));
                        break;
                    default:
                        break;
                }
                videoView.setMediaController(new MediaController(SecondFragment.this));
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}
