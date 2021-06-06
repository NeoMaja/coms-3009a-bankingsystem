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

public class adminRegisterTest {

    @Rule
    public ActivityTestRule<adminRegister> activityTestRule = new ActivityTestRule<adminRegister>(adminRegister.class);
    private adminRegister activity = null;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void testLauch(){
        assertNotNull( onView(withId(R.id.firstNameAdmin)));
        assertNotNull(onView(withId(R.id.lastNameAdmin)));
        assertNotNull(onView(withId(R.id.usernameAdmin)));
        assertNotNull(onView(withId(R.id.idNumberAdmin)));
        assertNotNull(onView(withId(R.id.lastNameAdmin)));
        assertNotNull(onView(withId(R.id.cellNumberAdmin)));
        assertNotNull(onView(withId(R.id.emailAdmin)));
        assertNotNull(onView(withId(R.id.passwordAdmin)));
        assertNotNull(onView(withId(R.id.confirmPasswordAdmin)));
    }


    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}