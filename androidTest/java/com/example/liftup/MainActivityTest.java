package com.example.liftup;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    private static final int SIMULATED_DELAY_MS = 3000;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void loginTest()
    {
        // Click login
        onView(withId(R.id.button_main_login)).perform(click());

//        try
//        {
//            Thread.sleep(SIMULATED_DELAY_MS);
//        } catch(InterruptedException e)
//        {
//
//        }

        // Check the page goes to MainActivity
        intended(hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void loginRegister()
    {
        // Click login
        onView(withId(R.id.button_main_register)).perform(click());

//        try
//        {
//            Thread.sleep(SIMULATED_DELAY_MS);
//        } catch(InterruptedException e)
//        {
//
//        }

        // Check the page goes to MainActivity
        intended(hasComponent(RegisterActivity.class.getName()));
    }
}