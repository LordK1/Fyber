package com.k1.fyber.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by K1 on 8/24/16.
 */
public class Information {

    @SerializedName("app_name")
    @Expose
    private String appName;

    @SerializedName("app_id")
    @Expose
    private Integer appId;

    @SerializedName("virtual_currency")
    @Expose
    private String virtualCurrency;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("support_url")
    @Expose
    private String supportUrl;

    @Override
    public String toString() {
        return "Information{" +
                "appName='" + appName + '\'' +
                ", appId=" + appId +
                ", virtualCurrency='" + virtualCurrency + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", supportUrl='" + supportUrl + '\'' +
                '}';
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }
}
