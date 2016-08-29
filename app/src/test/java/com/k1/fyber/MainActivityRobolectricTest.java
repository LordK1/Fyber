package com.k1.fyber;

import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by K1 on 8/28/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN)
public class MainActivityRobolectricTest {

    private MainActivity mActivity;

    @Before
    @Config(sdk = Build.VERSION_CODES.KITKAT)
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();

    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(mActivity);
    }

    @Test
    public void showHaveOfferFragment() throws Exception {
        assertNotNull(mActivity.getSupportFragmentManager().findFragmentById(R.id.fragment));
    }
}
