package com.evgkit.simpleandroidapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.evgkit.simpleandroidapp.R;
import com.evgkit.simpleandroidapp.service.CatService;

public class CatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                getResources().getBoolean(R.bool.isLandscape) ?
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) :
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(new CatsAdapter(CatService.getCats()));
    }
}
