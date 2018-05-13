package com.evgkit.simpleandroidapp.threads.primitiveAsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evgkit.simpleandroidapp.R;
import com.evgkit.simpleandroidapp.threads.Events;

public class AsyncTaskActivity extends AppCompatActivity implements Events {

    private Button createBtn;
    private Button startBtn;
    private Button cancelBtn;
    private TextView textView;

    private PrimitiveAsyncTask primitiveAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        Toast.makeText(this, this.getLocalClassName(), Toast.LENGTH_SHORT).show();

        createBtn = findViewById(R.id.buttonAsyncCreate);
        startBtn = findViewById(R.id.buttonAsyncStart);
        cancelBtn = findViewById(R.id.buttonAsyncCancel);
        textView = findViewById(R.id.textViewAsync);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAsyncCreate:
                        doAsyncTaskCreate();
                        break;

                    case R.id.buttonAsyncStart:
                        doAsyncTaskStart();
                        break;

                    case R.id.buttonAsyncCancel:
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
        primitiveAsyncTask = new PrimitiveAsyncTask(this);
    }

    private void doAsyncTaskStart() {
        if ((primitiveAsyncTask == null) || (primitiveAsyncTask.isCancelled())) {
            Toast.makeText(this, "You should Create AsyncTask", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "AsyncTask started", Toast.LENGTH_SHORT).show();
            primitiveAsyncTask.execute(1, 10);
        }
    }

    private void doAsyncTaskCancel() {
        primitiveAsyncTask.cancel(true);
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
        if (primitiveAsyncTask != null) {
            primitiveAsyncTask.cancel(false);
            primitiveAsyncTask = null;
        }
        super.onDestroy();
    }
}
