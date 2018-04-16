package com.example.amityapplabs.amityincubatorlistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.my_recycler_view);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myAdapter = new MyAdapter();
        myRecyclerView.setAdapter(myAdapter);
    }
}
