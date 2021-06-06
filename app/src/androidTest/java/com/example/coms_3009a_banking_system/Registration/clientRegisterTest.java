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
    public void testLauch(){
        assertNotNull( onView(withId(R.id.firstName)));
        assertNotNull(onView(withId(R.id.lastName)));
        assertNotNull(onView(withId(R.id.username)));
        assertNotNull(onView(withId(R.id.idNumber)));
        assertNotNull(onView(withId(R.id.cellNumber)));
        assertNotNull(onView(withId(R.id.email)));
        assertNotNull(onView(withId(R.id.p_number)));
        assertNotNull(onView(withId(R.id.confirmPassword)));
        assertNotNull(onView(withId(R.id._register)));

    }


    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}