package com.k1.fyber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * to show list of {@link com.k1.fyber.model.Offer} in {@link MainActivityFragment}
 */
public class MainActivityFragment extends Fragment {

    private View root;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        return root;
    }
}
