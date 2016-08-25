package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K1 on 8/24/16.
 */
@Parcel
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(List<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getPayout() {
        return payout;
    }

    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }
}
