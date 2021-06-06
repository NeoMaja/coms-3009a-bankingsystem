package com.example.coms_3009a_banking_system;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AdminProfilePageTest {

    @Rule
    public ActivityTestRule<AdminProfilePage> activityTestRule = new ActivityTestRule<AdminProfilePage>(AdminProfilePage.class);
    private AdminProfilePage activity = null;


    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void testLauch(){
        assertNotNull( onView(withId(R.id.firstName)));
        assertNotNull(onView(withId(R.id.lastName)));
        assertNotNull(onView(withId(R.id.username)));
        assertNotNull(onView(withId(R.id.useremail)));
        assertNotNull(onView(withId(R.id.p_number)));
        assertNotNull(onView(withId(R.id.admin_key)));
        assertNotNull(onView(withId(R.id.UpdateEarn)));
        assertNotNull(onView(withId(R.id.UpdateExpend)));
        assertNotNull(onView(withId(R.id.UpdateAddress)));
        assertNotNull(onView(withId(R.id.monEarn)));
        assertNotNull(onView(withId(R.id.Expend)));
        assertNotNull(onView(withId(R.id.profileAddress)));


    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}