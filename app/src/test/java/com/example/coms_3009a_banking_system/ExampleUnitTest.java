package com.example.coms_3009a_banking_system;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUser(){
        User testUser= new User("990011","Neoza","Nmail@gmail.com","0723456781");
        String  IDno, Fname ,email,Cno;
        //test F  Name
        Fname = testUser.getName();
        assertEquals("Neoza",Fname);
        //test Email
        email = testUser.getEmail();
        assertEquals("Nmail@gmail.com",email);
        //Test Id No
        IDno = testUser.getId();
        assertEquals("990011",IDno);
        // TEst Cellphone Number
        Cno = testUser.getPhoneNo();
        assertEquals("0723456781",Cno);
    }

    @Test
    public void testClient(){
        ClientUser testUser= new ClientUser("990011","Neoza","Nmail@gmail.com","0723456781");
        String  IDno, Fname ,email,Cno;
        //test F  Name
        Fname = testUser.getName();
        assertEquals("Neoza",Fname);
        //test Email
        email = testUser.getEmail();
        assertEquals("Nmail@gmail.com",email);
        //Test Id No
        IDno = testUser.getId();
        assertEquals("990011",IDno);
        // TEst Cellphone Number
        Cno = testUser.getPhoneNo();
        assertEquals("0723456781",Cno);
    }


}