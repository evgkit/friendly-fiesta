package com.evgkit.simpleandroidapp.ui.threads.threadsAsyncTask;

import android.os.SystemClock;

import com.evgkit.simpleandroidapp.ui.threads.Events;

public class ThreadsAsyncTaskImpl extends ThreadsAsyncTask<Integer> {

    private final Events events;

    public ThreadsAsyncTaskImpl(Events events) {
        this.events = events;
    }

    @Override
    protected Integer doInBackground() {
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
        if (events != null) {
            events.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute() {
        if (events != null) {
            events.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (events != null) {
            events.onProgressUpdate(values[0]);
        }
    }
}
