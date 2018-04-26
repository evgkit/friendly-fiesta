package com.evgkit.simpleandroidapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.evgkit.simpleandroidapp.R;

public class ThreadsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        Toast.makeText(this, this.getLocalClassName(), Toast.LENGTH_SHORT).show();
    }
}
