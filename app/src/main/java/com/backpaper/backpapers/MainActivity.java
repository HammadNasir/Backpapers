package com.backpaper.backpapers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.kc.unsplash.Unsplash;
import com.kc.unsplash.api.Order;
import com.kc.unsplash.models.Photo;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private Unsplash unsplash;
    private PhotoRecyclerAdapter adapter;
    public FloatingActionButton fab;
    private ProgressDialog mProgressDialog;
    private AsyncTask mMyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unsplash = new Unsplash("0d8e94bfcc40073814d3ea2a789425a3e4cc77727096a9c9c42e54ae74b94383");

        fab = (FloatingActionButton) findViewById(R.id.fab_download);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        adapter = new PhotoRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        unsplash.getPhotos(1, 10, Order.LATEST, new Unsplash.OnPhotosLoadedListener() {
            @Override
            public void onComplete(List<Photo> photos) {
                adapter.setPhotos(photos);
            }

            @Override
            public void onError(String error) {

            }
        });

    }

}
