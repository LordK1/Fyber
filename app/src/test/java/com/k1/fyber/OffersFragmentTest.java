package com.k1.fyber;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.k1.fyber.adapter.OffersAdapter;
import com.k1.fyber.model.OffersData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by K1 on 8/29/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class OffersFragmentTest {

    private MainActivity mainActivity;
    private OffersData data;
    private OffersAdapter offersAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        data = Mockito.mock(OffersData.class);
        offersAdapter = Mockito.mock(OffersAdapter.class);
        linearLayoutManager = Mockito.mock(LinearLayoutManager.class);

    }

    @Test
    public void validateView() {
        final Fragment fragment = mainActivity.getSupportFragmentManager().findFragmentById(R.id.fragment);
        assertNotNull(fragment);
        assertThat(fragment, instanceOf(OffersFragment.class));

        final RecyclerView mRecyclerView = (RecyclerView) mainActivity.findViewById(R.id.main_offers_recycler_view);
        assertNotNull(mRecyclerView);

    }

}