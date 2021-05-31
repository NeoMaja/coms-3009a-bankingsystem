package com.example.coms_3009a_banking_system.ClientAccount;

public class historyItem {
    private String reference;
    private String transaction_type;
    private String Amount;
    private String Date;

    public historyItem(String Reference, String Trans_type, String Amount, String Date) {
        this.reference = Reference;
        this.transaction_type = Trans_type;
        this.Amount = Amount;
        this.Date = Date;
    }
    public void changeText1(String text){
        reference = text;
    }


    public String getReference() {
        return reference;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public String getAmount(){
        return Amount;
    }

    public String getDate() {
        return Date;
    }
}
