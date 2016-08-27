package com.k1.fyber;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by K1 on 8/27/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 18, constants = BuildConfig.class)
public class OnOkClickListenerTest {



    @Before
    public void setup() throws Exception {
        assertEquals(4, 2 + 2);
//        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();

    }

    @Test
    public void mainActivityAppearsAsExpectedInitially() throws Exception {
//        Assert.assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.fab).getVisibility());
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        OffersFragment offersFragment = new OffersFragment();
        startFragment(offersFragment);
        assertNotNull(offersFragment);
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOnClick() throws Exception {


    }
}