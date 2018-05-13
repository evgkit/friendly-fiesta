package com.evgkit.simpleandroidapp.network;


import com.google.gson.annotations.SerializedName;

public class Gif {
    @SerializedName("id")
    private String id;

    @SerializedName("images")
    private ImagesStruct imagesStruct;

    public ImagesStruct getImagesStruct() {
        return imagesStruct;
    }
}
