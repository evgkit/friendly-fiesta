package com.evgkit.simpleandroidapp.ui;

import android.os.AsyncTask;
import android.os.SystemClock;

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private final AsyncTaskEvents asyncTaskEvents;

    public CounterAsyncTask(AsyncTaskEvents asyncTaskEvents) {
        this.asyncTaskEvents = asyncTaskEvents;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        for (int i = 0; i <= 10; i++) {
            if (isCancelled()) {
                return i;
            }

            publishProgress(i);
            SystemClock.sleep(1000);

        }
        return 10;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onCancelled(Integer aInteger) {
        super.onCancelled(aInteger);
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onCancel();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (asyncTaskEvents != null) {
            asyncTaskEvents.onCancel();
        }
    }
}
