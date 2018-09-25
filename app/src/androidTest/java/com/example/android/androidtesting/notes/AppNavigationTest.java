package com.example.android.androidtesting.notes;


import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.example.android.androidtesting.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;


/**
 * Tests for the {@link DrawerLayout} layout component in {@link NotesActivity} which manages
 * navigation within the app.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppNavigationTest {
    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<NotesActivity> mActivityTestRule =
            new ActivityTestRule<>(NotesActivity.class);

    /***
     * The navigation drawer to find a specific item that we want to select.
     */
    @Test
    public void clickOnStatisticsNavigationItem_ShowsStatisticsScreen() {
        // fail("Implement step 9");

        // Open Drawer to click on navigation.
        // Find the navigation drawer layout, check that it is closed
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start statistics screen.
        // Now that the drawer is open we need to find the "statistics" item in the drawer and select it.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.statistics_navigation_menu_item));

        // Check that statistics Activity was opened.
        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
                .getString(R.string.no_statistics_available);
        // Verify that the correct text message "No statistics" is displayed.
        onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));
    }

    /**
     * Test verifies that clicking the ‘Home' icon on the start screen opens the navigation drawer.
     */
    @Test
    public void clickOnAndroidHomeIcon_OpensNavigation() {
        //fail("Implement step 9");

        // Finds the navigation drawer layout and:
        // 1.Check that left drawer is closed at startup
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.

        // 2.Open Drawer
        String navigateUpDesc = mActivityTestRule.getActivity()
                .getString(android.support.v7.appcompat.R.string.abc_action_bar_up_description);
        onView(withContentDescription(navigateUpDesc)).perform(click());

        // 3.Check if drawer is open
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen(Gravity.LEFT))); // Left drawer is open open.
    }

}