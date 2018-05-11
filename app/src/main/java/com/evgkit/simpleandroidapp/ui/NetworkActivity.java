package com.evgkit.simpleandroidapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.evgkit.simpleandroidapp.R;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewNetwork);

        recyclerView.setLayoutManager(
                getResources().getBoolean(R.bool.isLandscape) ?
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) :
                        new LinearLayoutManager(this)
        );
    }
}
