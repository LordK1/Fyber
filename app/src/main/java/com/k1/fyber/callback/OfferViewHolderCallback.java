package com.k1.fyber.callback;

import com.k1.fyber.model.Offer;
import com.k1.fyber.model.Thumbnail;

/**
 * Created by K1 on 8/25/16.
 */
public interface OfferViewHolderCallback {

    /**
     * @param offer
     * @return
     */
    Offer onOfferClicked(Offer offer);


    void onOfferTitleClicked(String title);


    /**
     * @param thumbnail
     * @return
     */
    Offer onThumbnailClicked(Thumbnail thumbnail);
}
