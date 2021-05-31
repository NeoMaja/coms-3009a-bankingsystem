package com.example.coms_3009a_banking_system.ClientAccount;

public class Clientitem {
    private String mText0;
    private String mText1;
    private String mText2;

    public Clientitem(String Accounttype, String AccNumber, String Amount) {
        this.mText0 = Accounttype;
        this.mText1 = AccNumber;
        this.mText2 = Amount;
    }
    public void changeText1(String text){
        mText0 = text;
    }


    public String getmText0() {
        return mText0;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }
}

