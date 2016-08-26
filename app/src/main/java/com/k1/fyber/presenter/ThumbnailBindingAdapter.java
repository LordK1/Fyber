package com.k1.fyber.presenter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by K1 on 8/26/16.
 */
public class ThumbnailBindingAdapter {

    @BindingAdapter({"bind:thumbUrl", "bind:placeholder"})
    public static void loadThumbnail(ImageView imageView, String imageUrl, Drawable placeholder) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(placeholder)
                .noFade()
                .fit()
                .into(imageView);

    }
}
