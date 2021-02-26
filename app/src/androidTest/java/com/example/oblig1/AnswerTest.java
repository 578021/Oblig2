package com.example.oblig1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AnswerTest {

    @Rule // Gives us an instance of the class we are going to test
    public ActivityScenarioRule<QuizActivity> qActivity = new ActivityScenarioRule<>(QuizActivity.class);

    // We need this to be able to get the image
    private QuizActivity getActivity(ActivityScenarioRule<QuizActivity> activityScenarioRule) {
        AtomicReference<QuizActivity> activityRef = new AtomicReference<>(); // AtomicReference an object reference that may be updated automatically
        activityScenarioRule.getScenario().onActivity(activityRef::set); // Returns an given action on the activity main thread
        return activityRef.get();
    }
    // Checks that if we type wrong answer, we don't get any points
    @Test
    public void testWrongAnswer() {
        onView(withId(R.id.answerEditText1)).perform(typeText("Baby"), closeSoftKeyboard());
        onView(withId(R.id.answerButton)).perform(click());
        onView(withId(R.id.pointsTextView1)).check(matches(withText("0")));
        onView(withId(R.id.scoreTextView1)).check(matches(withText("1")));
    }
    // Checks that if we type correct answer, we get points and score is updated
    @Test
    public void testRightAnswer(){
        QuizActivity q = getActivity(qActivity);
            onView(withId(R.id.answerEditText1)).perform(replaceText(q.imageList
                    .get(q.pickedImage).name));
        onView(withId(R.id.answerButton)).perform(click());
        onView(withId(R.id.pointsTextView1)).check(matches(withText("1")));
        onView(withId(R.id.scoreTextView1)).check(matches(withText("1")));
    }
}
