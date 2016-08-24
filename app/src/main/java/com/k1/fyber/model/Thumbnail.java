package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 8/24/16.
 */
public class Thumbnail {

    @SerializedName("lowres")
    @Expose
    private String lowRes;

    @SerializedName("hires")
    @Expose
    private String highRes;

    @Override
    public String toString() {
        return "Thumbnail{" +
                "lowRes='" + lowRes + '\'' +
                ", highRes='" + highRes + '\'' +
                '}';
    }

    public String getLowRes() {
        return lowRes;
    }

    public void setLowRes(String lowRes) {
        this.lowRes = lowRes;
    }

    public String getHighRes() {
        return highRes;
    }

    public void setHighRes(String highRes) {
        this.highRes = highRes;
    }
}
