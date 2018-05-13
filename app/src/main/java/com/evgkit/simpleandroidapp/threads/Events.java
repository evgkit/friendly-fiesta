package com.evgkit.simpleandroidapp.threads;

public interface Events {
    void onPreExecute();

    void onPostExecute();

    void onProgressUpdate(Integer integer);

    void onCancel();
}
