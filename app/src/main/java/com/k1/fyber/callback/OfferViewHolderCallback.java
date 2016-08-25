package com.k1.fyber.callback;

import com.k1.fyber.model.Offer;

/**
 * Created by K1 on 8/25/16.
 */
public interface OfferViewHolderCallback {

    /**
     * @param offer
     * @return
     */
    Offer onOfferClicked(Offer offer);
}
