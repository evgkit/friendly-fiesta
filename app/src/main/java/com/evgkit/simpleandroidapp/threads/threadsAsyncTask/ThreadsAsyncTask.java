package com.evgkit.simpleandroidapp.threads.threadsAsyncTask;


import android.os.Handler;
import android.os.Looper;

public abstract class ThreadsAsyncTask<T> {
    protected volatile boolean isCancelled = false;

    private Thread backgroundThread;

    /**
     * Runs on new thread after {@link #onPostExecute()} and before {@link #onPostExecute()}.
     */
    protected abstract T doInBackground();

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     */
    protected abstract void onPreExecute();

    /**
     * Runs on the UI thread after {@link #doInBackground}
     */
    protected abstract void onPostExecute();

    public void execute() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                onPreExecute();
                backgroundThread = new Thread("Handler_executor_thread") {

                    @Override
                    public void run() {
                        doInBackground();
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                onPostExecute();
                            }
                        });
                    }
                };

                backgroundThread.start();
            }
        });
    }

    private void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void cancel() {
        isCancelled = true;
        if (backgroundThread != null) {
            backgroundThread.interrupt();
        }
    }

    protected void publishProgress(final T... values) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(values);
            }
        });
    }

    protected abstract void onProgressUpdate(T[] values);

    public boolean isCancelled() {
        return isCancelled;
    }
}
