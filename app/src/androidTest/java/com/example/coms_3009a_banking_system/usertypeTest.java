package com.example.coms_3009a_banking_system;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.coms_3009a_banking_system.login.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class usertypeTest {

    @Rule
    public ActivityScenarioRule<usertype> tUserTypeScenarioRule = new ActivityScenarioRule<usertype>(usertype.class);

    private ActivityScenario<usertype> tUser = null;

    @Before
    public void setUp() throws Exception {

        tUser = tUserTypeScenarioRule.getScenario();

    }

    @Test

    public void testLaunch(){

        assertNotNull( onView(withId(R.id.textView8)));
        assertNotNull(onView(withId(R.id.Admin_b)));
        assertNotNull(onView(withId(R.id.client_b)));

    }


    @After
    public void tearDown() throws Exception {

        tUser = null ;
    }
}