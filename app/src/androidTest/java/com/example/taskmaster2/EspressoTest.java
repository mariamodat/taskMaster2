package com.example.taskmaster2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
  @Rule
  public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void firstTest(){

  onView(withId(R.id.details1)).perform(click());
  onView(withId(R.id.taskText)).check( matches(withText("Task  1")) );

}



}
