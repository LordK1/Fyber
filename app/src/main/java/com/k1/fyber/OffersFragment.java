package com.k1.fyber;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.k1.fyber.adapter.OffersAdapter;
import com.k1.fyber.callback.OfferViewHolderCallback;
import com.k1.fyber.model.Offer;
import com.k1.fyber.model.OffersData;
import com.k1.fyber.model.Thumbnail;

import java.util.ArrayList;

/**
 * to show list of {@link com.k1.fyber.model.Offer} in {@link OffersFragment}
 */
public class OffersFragment extends Fragment implements OfferViewHolderCallback {

    private static final String TAG = OffersFragment.class.getSimpleName();
    private View root;
    private RecyclerView mRecyclerView;
    private ArrayList<Offer> list;
    private OffersAdapter adapter;
    private OffersData mOffersData;

    public OffersFragment() {
        list = new ArrayList<>();
        adapter = new OffersAdapter(list, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Imperfect: 8/26/16 OffersFragment onAttach
        Log.d(TAG, "onAttach() called with: " + "context = [" + context + "]");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = ["
                + container + "], savedInstanceState = [" + savedInstanceState + "]");
        root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.main_offers_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter.setHasStableIds(true);
        mRecyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Imperfect: 8/26/16 OffersFragment onActivityCreated
        Log.d(TAG, "onActivityCreated() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        /*if (savedInstanceState != null) {
            mOffersData = Parcels.unwrap(savedInstanceState.getParcelable("OffersData"));
            list = (ArrayList<Offer>) mOffersData.getOffers();
        }*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelable("OffersData", Parcels.wrap(mOffersData));
    }

    /**
     * Update list after
     *
     * @param offersData
     */
    public void updateList(OffersData offersData) {
        Log.d(TAG, "updateList() called with: " + "offersData = [" + offersData + "]");
        this.list.clear();
        this.list.addAll(offersData.getOffers());
        this.adapter.notifyDataSetChanged();
    }

    /**
     * When on {@link Offer} item clicked, it depends on the scenario you can change the reaction of
     * callback by adding your codes in this method
     *
     * @param offer
     * @return
     */
    @Override
    public Offer onOfferClicked(Offer offer) {
        Log.d(TAG, "onOfferClicked() called with: " + "offer = [" + offer + "]");
        Toast.makeText(getContext(), offer.toString(), Toast.LENGTH_SHORT).show();
        return offer;
    }

    @Override
    public void onOfferTitleClicked(String title) {
        Log.d(TAG, "onOfferTitleClicked() called with: " + "title = [" + title + "]");
    }

    @Override
    public Offer onThumbnailClicked(Thumbnail thumbnail) {
        Log.d(TAG, "onThumbnailClicked() called with: " + "thumbnail = [" + thumbnail + "]");
        return null;
    }


}
