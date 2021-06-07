/*package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.test.rule.ActivityTestRule;

import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Registration.adminRegister;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AccountActivityTest {

    @Rule
    public ActivityTestRule<AccountActivity> activityTestRule = new ActivityTestRule<AccountActivity>(AccountActivity.class);
    private AccountActivity activity = null;
    
    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }


    @Test
    public void testLauch(){
        assertNotNull( onView(withId(R.id.account_numberV)));
        assertNotNull(onView(withId(R.id.account_typeV)));
        assertNotNull(onView(withId(R.id.balanceV)));

    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}

 */