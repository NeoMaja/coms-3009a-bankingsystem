package com.example.coms_3009a_banking_system;

public class User {
    private String First_Name, ID_Number, Email, Cellphone_Number;

    public User(String ID_Number, String First_Name, String Email, String Cellphone_Number)
    {
        this.First_Name =First_Name;
        this.ID_Number = ID_Number;
        this.Email =Email;
        this.Cellphone_Number =Cellphone_Number;
        //this.Image = Image;
    }

    public String getId() {
        return ID_Number;
    }

    public void setId(String ID_Number) {
        this.ID_Number = ID_Number;
    }

    public String getName() {
        return First_Name;
    }

    public void setName(String First_Name) {
        this.First_Name =First_Name;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNo() {
        return Cellphone_Number;
    }

    public void setPhoneNo(String Cellphone_Number) {
        this.Cellphone_Number = Cellphone_Number;
    }

}
