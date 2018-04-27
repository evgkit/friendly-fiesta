package com.evgkit.simpleandroidapp.ui.threads.threadsAsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evgkit.simpleandroidapp.R;
import com.evgkit.simpleandroidapp.ui.threads.Events;

public class ThreadsActivity extends AppCompatActivity implements Events {

    private Button createBtn;
    private Button startBtn;
    private Button cancelBtn;
    private TextView textView;

    private ThreadsAsyncTask threadsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        Toast.makeText(this, this.getLocalClassName(), Toast.LENGTH_SHORT).show();

        createBtn = findViewById(R.id.buttonThreadsCreate);
        startBtn = findViewById(R.id.buttonThreadsStart);
        cancelBtn = findViewById(R.id.buttonThreadsCancel);
        textView = findViewById(R.id.textViewThreads);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonThreadsCreate:
                        doAsyncTaskCreate();
                        break;

                    case R.id.buttonThreadsStart:
                        doAsyncTaskStart();
                        break;

                    case R.id.buttonThreadsCancel:
                        doAsyncTaskCancel();
                        break;
                }
            }
        };
        createBtn.setOnClickListener(onClickListener);
        startBtn.setOnClickListener(onClickListener);
        cancelBtn.setOnClickListener(onClickListener);
    }

    private void doAsyncTaskCreate() {
        Toast.makeText(this, "AsyncTask created", Toast.LENGTH_SHORT).show();
        threadsAsyncTask = new ThreadsAsyncTaskImpl(this);
    }

    private void doAsyncTaskStart() {
        if ((threadsAsyncTask == null) || (threadsAsyncTask.isCancelled())) {
            Toast.makeText(this, "You should Create AsyncTask", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "AsyncTask started", Toast.LENGTH_SHORT).show();
            threadsAsyncTask.execute();
        }
    }

    private void doAsyncTaskCancel() {
        threadsAsyncTask.cancel();
    }

    @Override
    public void onPreExecute() {
        Toast.makeText(this, "onPreExecute", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText(this, "onPostExecute", Toast.LENGTH_SHORT).show();
        textView.setText("Done!");
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        textView.setText(String.valueOf(integer));
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "onCancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (threadsAsyncTask != null) {
            threadsAsyncTask.cancel();
            threadsAsyncTask = null;
        }
        super.onDestroy();
    }
}
