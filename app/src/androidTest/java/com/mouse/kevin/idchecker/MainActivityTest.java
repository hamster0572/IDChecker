package com.mouse.kevin.idchecker;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private String correctIndividualIDToBeTyped;
    private String incorrectIndividualIDToBeTyped;
    private String correctCoporateIDToBeTyped;
    private String incorrectCoporateIDToBeTyped;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
        correctIndividualIDToBeTyped = "A123456789";
        incorrectIndividualIDToBeTyped = "A123456788";
        correctCoporateIDToBeTyped = "96385215";
        incorrectCoporateIDToBeTyped = "98765432";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHint() throws Exception {
        onView(withId(R.id.input_ID)).check(matches(withHint(R.string.ID_String)));
    }

    @Test
    public void testCorrectIndividualID() throws Exception {
        onView(withId(R.id.input_ID)).perform(typeText(correctIndividualIDToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.text_msg)).check(matches(withText(R.string.msg_ID_valid)));
        onView(withId(R.id.btn_clear)).perform(click());
        onView(withId(R.id.text_msg)).check(matches(withText("")));
        onView(withId(R.id.input_ID)).check(matches(withHint(R.string.ID_String)));
    }

    @Test
    public void testIncorrectIndividualID() throws Exception {
        onView(withId(R.id.input_ID)).perform(typeText(incorrectIndividualIDToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.text_msg)).check(matches(withText(R.string.msg_ID_invalid)));
        onView(withId(R.id.btn_clear)).perform(click());
        onView(withId(R.id.text_msg)).check(matches(withText("")));
        onView(withId(R.id.input_ID)).check(matches(withHint(R.string.ID_String)));
    }

    @Test
    public void testCorrectCorporateID() throws Exception {
        onView(withId(R.id.input_ID)).perform(typeText(correctCoporateIDToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.text_msg)).check(matches(withText(R.string.msg_ID_valid)));
        onView(withId(R.id.btn_clear)).perform(click());
        onView(withId(R.id.text_msg)).check(matches(withText("")));
        onView(withId(R.id.input_ID)).check(matches(withHint(R.string.ID_String)));
    }

    @Test
    public void testIncorrectCorporateID() throws Exception {
        onView(withId(R.id.input_ID)).perform(typeText(incorrectCoporateIDToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.text_msg)).check(matches(withText(R.string.msg_ID_invalid)));
        onView(withId(R.id.btn_clear)).perform(click());
        onView(withId(R.id.text_msg)).check(matches(withText("")));
        onView(withId(R.id.input_ID)).check(matches(withHint(R.string.ID_String)));
    }

}