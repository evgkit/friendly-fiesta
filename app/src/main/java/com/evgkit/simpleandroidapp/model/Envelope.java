package com.evgkit.simpleandroidapp.model;


import com.google.gson.annotations.SerializedName;

public class Envelope<T> {
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }
}
