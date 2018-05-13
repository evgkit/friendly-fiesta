package com.evgkit.simpleandroidapp.cat;

import android.support.annotation.NonNull;

public class Cat {
    @NonNull
    private final String url;

    public Cat(@NonNull String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
