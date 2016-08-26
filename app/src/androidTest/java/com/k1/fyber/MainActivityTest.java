package com.k1.fyber;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by K1 on 8/26/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(
            MainActivity.class
    );
    private String mStringToByond;

    @Before
    public void initValidString() {
        mStringToByond = "Espresso";
    }

    @Test
    public void changeTest_sameActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.offer_title_text_view))
                .perform(ViewActions.typeText(
                        mStringToByond
                ));

    }


}
