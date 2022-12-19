package bhusal.ujjwal.androidproject

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import bhusal.ujjwal.androidproject.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class TestLogin {

    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginUI(){
        onView(withId(R.id.etUsername))
            .perform(typeText("crow"))

        onView(withId(R.id.etPassword))
            .perform(typeText("1234abcd"))

        closeSoftKeyboard()

        onView(withId(R.id.btnLogin))
            .perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.txt_welcome))
            .check(matches(withText("Welcome")))

    }
}

