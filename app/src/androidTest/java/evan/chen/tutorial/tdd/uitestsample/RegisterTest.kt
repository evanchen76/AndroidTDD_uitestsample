package evan.chen.tutorial.tdd.uitestsample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

@LargeTest
class RegisterTest {

    @Rule
    @JvmField
    var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun rightPassword_should_startActivity() {
        //type "a123456789" on loginId
        onView(withId(R.id.loginId)).perform(typeText("a123456789"), ViewActions.closeSoftKeyboard())
        //type "a111111111" on password
        onView(withId(R.id.password)).perform(typeText("a111111111"), ViewActions.closeSoftKeyboard())
        //Click send button
        onView(withId(R.id.send)).perform(click())
        //verify screen should display "Sign Up Success"
        onView(withText("Sign Up Success")).check(matches(isDisplayed()))
    }

    private fun inputRightRegisterData() {
        onView(withId(R.id.loginId)).perform(typeText("a123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("a111111111"), ViewActions.closeSoftKeyboard())
    }

    @Test
    fun wrongPassword_should_alert() {
        //type login id
        onView(withId(R.id.loginId)).perform(typeText("a123456789"), ViewActions.closeSoftKeyboard())
        //type password
        onView(withId(R.id.password)).perform(typeText("1234"), ViewActions.closeSoftKeyboard())    //點選註冊按鈕
        onView(withId(R.id.send)).perform(click())

        //register fail should alert
        onView(withText("Error"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    private fun inputWrongRegisterData() {
        onView(withId(R.id.loginId)).perform(typeText("a123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("1234"), ViewActions.closeSoftKeyboard())
    }
}