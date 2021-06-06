package com.example.coms_3009a_banking_system.Registration;

import androidx.test.rule.ActivityTestRule;

import com.example.coms_3009a_banking_system.R;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;



public class clientRegisterTest {

    @Rule
    public ActivityTestRule<clientRegister> activityTestRule = new ActivityTestRule<clientRegister>(clientRegister.class);
    private clientRegister activity = null;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        assertNotNull(activity.findViewById(R.id.scrollView3));
        assertNotNull(activity.findViewById(R.id.firstName));
        assertNotNull(activity.findViewById(R.id.lastName));
        assertNotNull(activity.findViewById(R.id.username));
        assertNotNull(activity.findViewById(R.id.idNumber));
        assertNotNull(activity.findViewById(R.id.cellNumber));
        assertNotNull(activity.findViewById(R.id.email));
        assertNotNull(activity.findViewById(R.id.p_number));
        assertNotNull(activity.findViewById(R.id.confirmPassword));
        assertNotNull(activity.findViewById(R.id._register));

    }


    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}