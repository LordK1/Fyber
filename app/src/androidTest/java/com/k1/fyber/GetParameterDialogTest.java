package com.k1.fyber;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by K1 on 8/29/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class GetParameterDialogTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private MainActivity mainActivity;

    @Before
    public void setActivity() {
        mainActivity = mActivityRule.getActivity();
    }

    @Test
    public void openFragment() throws Exception {
        final ViewInteraction fabViewInteraction = onView(withId(R.id.fab)).check(matches(isCompletelyDisplayed()));
        fabViewInteraction.perform(click());
        onView(withId(R.id.fragment_get_parameter)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.get_app_id_edit_input)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.get_api_key_edit_input)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.get_pub_edit_input)).check(matches(isCompletelyDisplayed()));

    }


}
