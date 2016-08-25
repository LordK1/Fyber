package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by K1 on 8/24/16.
 */
@Parcel
public class OfferType {

    @SerializedName("offer_type_id")
    @Expose
    private Integer offerTypeId;

    @SerializedName("readable")
    @Expose
    private String readable;

    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
