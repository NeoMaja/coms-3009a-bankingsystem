package com.example.coms_3009a_banking_system.login;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.login.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class Login2Test {

    @Rule
    public ActivityScenarioRule<Login2> tLoginScenarioRule = new ActivityScenarioRule<Login2>(Login2.class);
    private ActivityScenario<Login2> tLogin = null;

    @Before
    public void setUp() throws Exception {

        tLogin = tLoginScenarioRule.getScenario();

    }

    @Test

    public void testLaunch(){

        assertNotNull( onView(withId(R.id.textView3)));
        assertNotNull(onView(withId(R.id.username)));
        assertNotNull(onView(withId(R.id.textInputLayout)));
        assertNotNull(onView(withId(R.id.textView)));
        assertNotNull(onView(withId(R.id.password_l)));
        assertNotNull(onView(withId(R.id.login_id)));
        assertNotNull(onView(withId(R.id.new_acc_id)));

    }


    @After
    public void tearDown() throws Exception {

        tLogin = null ;
    }
}




/*package com.example.coms_3009a_banking_system.login;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.coms_3009a_banking_system.Client_Verification_Status;
import com.example.coms_3009a_banking_system.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class Login2Test {
    @Rule
    public ActivityTestRule<Login2>login2ActivityTestRule = new ActivityTestRule<Login2>(Login2.class);
    private Login2 login2 = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Client_Verification_Status.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        login2 = login2ActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfSecondsOnButtonClick(){
        assertNotNull(login2.findViewById(R.id.login_id));
        onView(withId(R.id.login_id)).perform(click());
        Activity Client_Verification_Status = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(Client_Verification_Status);

        Client_Verification_Status.finish();

    }

    @After
    public void tearDown() throws Exception {
        login2 = null;
    }
}
*/