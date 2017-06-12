package studytutorial.in.linechart;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by a634941 on 01-06-2017.
 */


@RunWith(AndroidJUnit4.class)
public class SampleTest1 {

    @Rule
    public final ActivityRule<MainActivity> main=new ActivityRule<>(MainActivity.class);

    @Test
    public void shouldBeAbleToLaunchMainScreen()
    {
        final ViewInteraction button = onView(withText("Button")).check(ViewAssertions.matches(isDisplayed()));
    }
}
