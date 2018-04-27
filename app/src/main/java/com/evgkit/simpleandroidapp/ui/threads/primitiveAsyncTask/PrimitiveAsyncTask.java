package com.evgkit.simpleandroidapp.ui.threads.primitiveAsyncTask;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.evgkit.simpleandroidapp.ui.threads.Events;

public class PrimitiveAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private final Events events;

    public PrimitiveAsyncTask(Events events) {
        this.events = events;
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
        if (events != null) {
            events.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (events != null) {
            events.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (events != null) {
            events.onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onCancelled(Integer aInteger) {
        super.onCancelled(aInteger);
        if (events != null) {
            events.onCancel();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (events != null) {
            events.onCancel();
        }
    }
}
