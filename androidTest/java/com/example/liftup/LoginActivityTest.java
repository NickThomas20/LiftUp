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
public class LoginActivityTest {
    private static final int SIMULATED_DELAY_MS = 3000;

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public IntentsTestRule<ProfileActivity> intentsTestRule = new IntentsTestRule<>(ProfileActivity.class);

    @Test
    public void loginSuccess()
    {
        // Type in username and password
        onView(withId(R.id.login_login_username))
                .perform(typeText("a"), closeSoftKeyboard());
        onView(withId(R.id.login_login_password))
                .perform(typeText("a"), closeSoftKeyboard());

        // Click login
        onView(withId(R.id.login_button_login)).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page goes to MainActivity
        intended(hasComponent(ProfileActivity.class.getName()));
    }

    @Test (expected= AssertionFailedError.class)
    public void loginFail() {
        // Type in username and password
        onView(withId(R.id.login_login_username))
                .perform(typeText("a"), closeSoftKeyboard());
        onView(withId(R.id.login_login_password))
                .perform(typeText("bbbbb"), closeSoftKeyboard());

        // Click login
        onView(withId(R.id.login_button_login)).perform(click());

        try
        {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch(InterruptedException e)
        {

        }

        // Check the page doesn't go to MainActivity
        intended(hasComponent(ProfileActivity.class.getName()));
    }
}