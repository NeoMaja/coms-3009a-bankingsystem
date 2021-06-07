/*package com.example.coms_3009a_banking_system.Registration;

import androidx.test.rule.ActivityTestRule;

import com.example.coms_3009a_banking_system.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AdminKeyViewTest {

    @Rule
    public ActivityTestRule<AdminKeyView> activityTestRule = new ActivityTestRule<AdminKeyView>(AdminKeyView.class);
    private AdminKeyView activity = null;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(onView(withId(R.id.enter_key_log)));
        assertNotNull(onView(withId(R.id.adminlog_key)));
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}

 */