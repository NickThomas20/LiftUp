package com.example.liftup;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import junit.framework.AssertionFailedError;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ProfileActivityTest {
    private static final int SIMULATED_DELAY_MS = 1000;

    @Rule
    public ActivityTestRule<ProfileActivity> activityRule = new ActivityTestRule<>(ProfileActivity.class);

    @Rule
    public IntentsTestRule<EditProfileActivity> intentsTestRule = new IntentsTestRule<>(EditProfileActivity.class);


    //button_profile_editInfo
    //button_stats websocket
    //button_stats2 lifts
    //nutbutton
    @Test
    public void editInfo()
    {

        // Click login
        onView(withId(R.id.button_profile_editInfo )).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page goes to MainActivity
        intended(hasComponent(EditProfileActivity.class.getName()));
    }

    @Test
    public void websockets()
    {

        // Click login
        onView(withId(R.id.button_stats )).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page goes to MainActivity
        intended(hasComponent(Websocket.class.getName()));
    }

    @Test
    public void lifts()
    {

        // Click login
        onView(withId(R.id.button_stats2 )).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page goes to MainActivity
        intended(hasComponent(liftsView.class.getName()));
    }

    @Test
    public void nutrition()
    {

        // Click login
        onView(withId(R.id.nutbutton )).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page goes to MainActivity
        intended(hasComponent(showNutrition.class.getName()));
    }
}