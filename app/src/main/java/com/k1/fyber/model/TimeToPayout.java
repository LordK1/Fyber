package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 8/24/16.
 */
public class TimeToPayout {

    @SerializedName("amount")
    @Expose
    private Integer amount;

    @SerializedName("readable")
    @Expose
    private String readable;

    @Override
    public String toString() {
        return "TimeToPayout{" +
                "amount=" + amount +
                ", readable='" + readable + '\'' +
                '}';
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
