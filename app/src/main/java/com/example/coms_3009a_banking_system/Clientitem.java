package com.example.coms_3009a_banking_system;

public class Clientitem {
    private String mText0;
    private String mText1;
    private String mText2;

    public Clientitem(String mText0, String mText1, String mText2) {
        this.mText0 = mText0;
        this.mText1 = mText1;
        this.mText2 = mText2;
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
