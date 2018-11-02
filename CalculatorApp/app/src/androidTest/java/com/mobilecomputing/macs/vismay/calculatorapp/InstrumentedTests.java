package com.mobilecomputing.macs.vismay.calculatorapp;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testIntegerAddition() {
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("112")));
    }

    @Test
    public void testDecimalAddition() {
        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnSix)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnEight)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("51.409")));
    }

    @Test
    public void testIntegerSubtraction() {
        onView(withId(R.id.btnEight)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnSubtract)).perform(click());
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("700")));
    }

    @Test
    public void testDecimalSubtraction() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnSubtract)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnEight)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnSeven)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("-43.500")));
    }

    @Test
    public void testIntegerMultiplication() {
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnMultiply)).perform(click());
        onView(withId(R.id.btnSix)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("300")));
    }

    @Test
    public void testDecimalMultiplication() {
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnMultiply)).perform(click());
        onView(withId(R.id.btnSix)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnThree)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("15.750")));
    }

    @Test
    public void testIntegerDivision() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView(withId(R.id.btnDivide)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("20")));
    }

    @Test
    public void testDecimalDivision() {
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.btnDivide)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("1.940")));
    }

    @Test
    public void testDivisionByZero() {
        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.btnDivide)).perform(click());
        onView(withId(R.id.btnZero)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("Infinity")));
    }

    @Test
    public void testMultiOperandExpression() {
        onView(withId(R.id.btnSeven)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMultiply)).perform(click());
        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnSubtract)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnDivide)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView((withId(R.id.btnEquals))).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("17")));
    }

    @Test
    public void testInvertSignOperation() {
        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnInvertSign)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnEquals)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("-33")));
    }

    @Test
    public void testMemoryRecallOperation() {
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMPlus)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("95.0")));
    }

    @Test
    public void testMemoryPlusOperation() {
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnMPlus)).perform(click());
        onView(withId(R.id.btnClearScreen)).perform(click());
        onView(withId(R.id.btnSix)).perform(click());
        onView(withId(R.id.btnMPlus)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("15.0")));
    }

    @Test
    public void testMemoryMinusOperation() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMMinus)).perform(click());
        onView(withId(R.id.btnClearScreen)).perform(click());
        onView(withId(R.id.btnFour)).perform(click());
        onView(withId(R.id.btnMMinus)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("-19.0")));
    }

    @Test
    public void testMemoryClearOperation() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMMinus)).perform(click());
        onView(withId(R.id.btnMClear)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("0.0")));
    }

    @Test
    public void testMemoryRecallAsFirstOperandInExpression() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMPlus)).perform(click());
        onView(withId(R.id.btnClearScreen)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.btnMultiply)).perform(click());
        onView(withId(R.id.btnTwo)).perform(click());
        onView(withId(R.id.btnDecimal)).perform(click());
        onView(withId(R.id.btnThree)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnNine)).perform(click());
        onView(withId(R.id.btnEquals)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("35.385")));
    }

    @Test
    public void testMemoryRecallAsSecondOperandInExpression() {
        onView(withId(R.id.btnOne)).perform(click());
        onView(withId(R.id.btnFive)).perform(click());
        onView(withId(R.id.btnMPlus)).perform(click());
        onView(withId(R.id.btnClearScreen)).perform(click());
        onView(withId(R.id.btnFour)).perform(click());
        onView(withId(R.id.btnMultiply)).perform(click());
        onView(withId(R.id.btnMRecall)).perform(click());
        onView(withId(R.id.btnEquals)).perform(click());
        onView(withId(R.id.portraitModeDisplay))
                .check(matches(withText("60")));
    }
}
