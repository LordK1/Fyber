package com.k1.fyber.api;

import com.google.gson.JsonObject;
import com.k1.fyber.model.OffersData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by K1 on 8/23/16.
 */
public interface ApiService {


    /**
     * http://api.fyber.com/feed/v1/offers.json?
     * appid=[APP_ID]&
     * uid=[USER_ID]&
     * ip=[IP_ADDRESS]&
     * locale=[LOCALE]&
     * device_id=[DEVICE_ID]&
     * ps_time=[TIMESTAMP]&
     * pub0=[CUSTOM]&
     * timestamp=[UNIX_TIMESTAMP]&
     * offer_types=[OFFER_TYPES]&
     * google_ad_id=[GAID]&
     * google_ad_id_limited_tracking_enabled=[GAID ENABLED]&
     * hashkey=[HASHKEY]
     * <p>
     * <p>
     * appid: 2070
     * uid: spiderman
     * locale: ‘DE’
     * ip: ‘109.235.143.113’
     * API Key: 1c915e3b5d42d05136185030892fbb846c278927
     * <p>
     * <p>
     * String sample = new StringBuilder()
     * .append("appid=2070&")
     * .append("device_id=0e40c2eb3bd7453dbfbdc00bccda85ad&")
     * .append("ip=109.235.143.113&")
     * .append("locale=de&")
     * .append("uid=spiderman&")
     * .append("1c915e3b5d42d05136185030892fbb846c278927")
     *
     * @return
     */
    @GET("offers.json")
    Call<JsonObject> getList(
            @Query("appid") String appID,
            @Query("ip") String ip,
            @Query("locale") String locale,
            @Query("uid") String uid,
            @Query("hashkey") String hasKey
    );

    @GET("offers.json")
    Call<OffersData> getListWithParams(@QueryMap Map<String, String> params);


}
