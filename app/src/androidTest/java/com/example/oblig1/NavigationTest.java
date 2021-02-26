package com.example.oblig1;

import android.app.Activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

public class NavigationTest {


    @Rule // Gives us an instance of the class we are going to test
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test // Checking if the displayed matches the infoActivity. Alternative we could test if the infoActivity starts with intent resolves
    // you could insert that you are in the new activity this way
    public void testNavigation(){
        onView(withId(R.id.infoButton1)).perform(click());
        onView(withId(R.id.infoActivity)).check(matches(isDisplayed()));
    }
}
