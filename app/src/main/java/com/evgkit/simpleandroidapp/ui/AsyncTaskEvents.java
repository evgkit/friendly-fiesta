package com.evgkit.simpleandroidapp.ui;

public interface AsyncTaskEvents {
    void onPreExecute();

    void onPostExecute();

    void onProgressUpdate(Integer integer);

    void onCancel();
}
