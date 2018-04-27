package com.evgkit.simpleandroidapp.ui.threads;

public interface Events {
    void onPreExecute();

    void onPostExecute();

    void onProgressUpdate(Integer integer);

    void onCancel();
}
