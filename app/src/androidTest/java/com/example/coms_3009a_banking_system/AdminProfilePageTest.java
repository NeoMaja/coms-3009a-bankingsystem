/*package com.example.coms_3009a_banking_system;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import com.example.coms_3009a_banking_system.login.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AdminProfilePageTest {

    @Rule
    public ActivityScenarioRule<AdminProfilePage> activityTestRule = new ActivityScenarioRule<AdminProfilePage>(AdminProfilePage.class);
    private ActivityScenario<AdminProfilePage> activity = null;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getScenario();
    }

    @Test
    public void testLaunch(){
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

 */