package com.evgkit.simpleandroidapp.ui;

import android.support.annotation.NonNull;

import com.evgkit.simpleandroidapp.cat.Cat;

public interface OnItemClickListener {
    void onItemClick(@NonNull Cat cat, int position);
}
