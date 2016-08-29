package com.k1.fyber;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

/**
 * To check the {@link MainActivity} {@link android.support.design.widget.FloatingActionButton}
 * Created by K1 on 8/26/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(
            MainActivity.class
    );

    public MainActivityTest(String pkg, Class<MainActivity> activityClass) {
        super(pkg, activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Test
    public void test_list_shown() {
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
    }


}
