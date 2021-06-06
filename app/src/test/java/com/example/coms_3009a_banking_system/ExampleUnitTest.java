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
        Fname = testUser.getName();
        assertEquals("Neoza",Fname);
        email = testUser.getEmail();
        assertEquals("Nmail@gmail.com",email);
        IDno = testUser.getName();
        assertEquals("990011",IDno);
        Cno = testUser.getName();
        assertEquals("0723456781",Cno);
    }


}