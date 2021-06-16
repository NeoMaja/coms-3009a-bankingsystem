package com.example.coms_3009a_banking_system;

import com.example.coms_3009a_banking_system.ClientAccount.Clientitem;
import com.example.coms_3009a_banking_system.ClientAccount.historyItem;

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

    @Test
    public void testClientitem(){
        Clientitem testUser= new Clientitem("Investment","992218614","500");
        String  accountType, Amount ,accountNumber;
        //testing Account Type.
        accountType = testUser.getmText0();
        assertEquals("Investment",accountType);
        //testing account Number.
        accountNumber = testUser.getmText1();
        assertEquals("992218614",accountNumber);
        //Testing Amount.
        Amount = testUser.getmText2();
        assertEquals("500",Amount);
    }
    @Test
    public void testhistoryItem(){
        historyItem testHistory = new historyItem("Car repair","Pay","500","2021-06-16");
        String Reference,Trans_type,Amount,Date;

        Reference = testHistory.getReference();
        assertEquals("Car repair",Reference);

        Trans_type = testHistory.getTransaction_type();
        assertEquals("Pay",Trans_type);

        Amount = testHistory.getAmount();
        assertEquals("500",Amount);

        Date = testHistory.getDate();
        assertEquals("2021-06-16",Date);


    }

    @Test
    public void testCreditClass(){
        CreditClass testHistory = new CreditClass("1988282","Xolani","Nqubezelo","133 Violet street","15000","5000");
        String First_Name, Last_Name, ID_Number, Residence_Address , Monthly_Expenditure, Monthly_Earnings;

        First_Name = testHistory.getFirst_Name();
        assertEquals("Xolani",First_Name);

        Last_Name = testHistory.getLast_Name();
        assertEquals("Nqubezelo",Last_Name);

        ID_Number = testHistory.getID_Number();
        assertEquals("1988282",ID_Number);

        Residence_Address = testHistory.getResidence_Address();
        assertEquals("133 Violet street",Residence_Address);

        Monthly_Expenditure = testHistory.getMonthly_Expenditure();
        assertEquals("5000",Monthly_Expenditure);

        Monthly_Earnings = testHistory.getMonthly_Earnings();
        assertEquals("15000",Monthly_Earnings);

    }



}