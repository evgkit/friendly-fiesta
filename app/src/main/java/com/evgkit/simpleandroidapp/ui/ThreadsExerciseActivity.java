package com.evgkit.simpleandroidapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.evgkit.simpleandroidapp.R;

public class ThreadsExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads_exercise);

        Button asyncTaskBtn = findViewById(R.id.buttonAsyncTask);
        asyncTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ThreadsExerciseActivity.this,
                        AsyncTaskActivity.class)
                );
            }
        });

        Button threadsBtn = findViewById(R.id.buttonThreads);
        threadsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        ThreadsExerciseActivity.this,
                        ThreadsActivity.class
                ));
            }
        });
    }
}
