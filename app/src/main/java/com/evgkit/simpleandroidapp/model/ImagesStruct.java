package com.evgkit.simpleandroidapp.model;


import com.google.gson.annotations.SerializedName;

public class ImagesStruct {
    @SerializedName("480w_still") private Image preview;

    public Image getPreview() {
        return preview;
    }
}
