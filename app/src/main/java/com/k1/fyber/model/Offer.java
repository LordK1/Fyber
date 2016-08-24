package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K1 on 8/24/16.
 */
public class Offer {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("offer_id")
    @Expose
    private Integer offerId;

    @SerializedName("teaser")
    @Expose
    private String teaser;

    @SerializedName("required_actions")
    @Expose
    private String requiredActions;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("offer_types")
    @Expose
    private List<OfferType> offerTypes = new ArrayList<>();

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    @SerializedName("payout")
    @Expose
    private Integer payout;

    @SerializedName("time_to_payout")
    @Expose
    private TimeToPayout timeToPayout;
}
