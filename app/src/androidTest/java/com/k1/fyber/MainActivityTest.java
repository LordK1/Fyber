package com.k1.fyber;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.k1.fyber.model.Offer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

/**
 * To check the {@link MainActivity} {@link android.support.design.widget.FloatingActionButton}
 * Created by K1 on 8/26/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void initValidData() throws Exception {

    }


    @Test
    public void test_list_shown() {
        onView(withId(R.id.main_offers_recycler_view)).check(matches(isDisplayed()));
        onData(is(instanceOf(Offer.class)));
    }


}
