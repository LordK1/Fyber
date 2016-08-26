package com.k1.fyber.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.k1.fyber.BR;
import com.k1.fyber.OffersFragment;
import com.k1.fyber.R;
import com.k1.fyber.callback.OfferViewHolderCallback;
import com.k1.fyber.model.Offer;
import com.k1.fyber.model.Thumbnail;

import java.util.List;

/**
 * To adopt list of {@link Offer} in {@link OffersFragment}
 */
public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {

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

    /**
     * extended {@link android.support.v7.widget.RecyclerView.ViewHolder} to render
     * {@link com.k1.fyber.model.Offer} items into views
     * to show fields of
     * {@link Offer#getTitle()}
     * {@link Thumbnail#getHighRes()}
     * {@link Offer#getTeaser()}
     * {@link Offer#getPayout()}
     * {@link Offer#getTimeToPayout()}
     */
    static class OfferViewHolder extends RecyclerView.ViewHolder {

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
