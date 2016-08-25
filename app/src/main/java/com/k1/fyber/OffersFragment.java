package com.k1.fyber;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.k1.fyber.callback.OfferViewHolderCallback;
import com.k1.fyber.model.Offer;
import com.k1.fyber.model.Offers;

import java.util.ArrayList;
import java.util.List;

/**
 * to show list of {@link com.k1.fyber.model.Offer} in {@link OffersFragment}
 */
public class OffersFragment extends Fragment implements OfferViewHolderCallback {

    public static final String FRAGMENT_NAME = OffersFragment.class.getName();
    private static final String TAG = OffersFragment.class.getSimpleName();
    private View root;
    private RecyclerView mRecyclerView;
    private ArrayList<Offer> list;
    private OffersAdapter adapter;

    public OffersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.main_offers_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        adapter = new OffersAdapter(list, this);
        mRecyclerView.setAdapter(adapter);
        return root;
    }

    /**
     * Update list after
     *
     * @param offers
     */
    public void updateList(Offers offers) {
        Log.d(TAG, "updateList() called with: " + "offers = [" + offers + "]");
        this.list.addAll(offers.getOffers());
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public Offer onOfferClicked(Offer offer) {
        Log.d(TAG, "onOfferClicked() called with: " + "offer = [" + offer + "]");
        Toast.makeText(getContext(), offer.toString(), Toast.LENGTH_SHORT).show();
        return offer;
    }

    /**
     * To adopt list of {@link Offer} in {@link OffersFragment}
     */
    private class OffersAdapter extends RecyclerView.Adapter<OfferViewHolder> {

        private final List<Offer> offers;
        private final OfferViewHolderCallback callback;

        public OffersAdapter(List<Offer> offers, OfferViewHolderCallback callback) {
            this.offers = offers;
            this.callback = callback;
        }

        @Override
        public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewDataBinding view = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.offer_view_holder, parent, false);
            return new OfferViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OfferViewHolder holder, int position) {
            final ViewDataBinding dataBinding = holder.getDataBinding();
            // bind the variables with data
            dataBinding.setVariable(BR.offer, getItem(position));
            dataBinding.setVariable(BR.callback, this.callback);

        }

        private Offer getItem(int position) {
            return offers.get(position);
        }

        @Override
        public int getItemCount() {
            return offers.size();
        }
    }

    /**
     * extended {@link android.support.v7.widget.RecyclerView.ViewHolder} to render
     * {@link com.k1.fyber.model.Offer} items into views
     */
    private class OfferViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding dataBinding;

        public OfferViewHolder(ViewDataBinding view) {
            super(view.getRoot());
            dataBinding = view;
        }

        public ViewDataBinding getDataBinding() {
            return dataBinding;
        }
    }
}
