package com.example.coms_3009a_banking_system;

public class CreditClass {
    private String First_Name, Last_Name, ID_Number, Residence_Address , Monthly_Expenditure, Monthly_Earnings;
   // private int  Monthly_Expenditure, Monthly_Earnings;

    public CreditClass(String ID_Number, String First_Name, String Last_Name, String Residence_Address, String Monthly_Earnings, String Monthly_Expenditure)
    {
        this.First_Name =First_Name;
        this.ID_Number = ID_Number;
        this.Last_Name = Last_Name;
        this.Residence_Address = Residence_Address;
        this.Monthly_Earnings = Monthly_Earnings;
        this.Monthly_Expenditure = Monthly_Expenditure;

    }

    public String getFirst_Name(){
        return First_Name;
    }
    public void setFirst_Name(String First_Name){
        this.First_Name = First_Name;
    }
    public String getLast_Name(){
        return Last_Name;
    }
    public void setLast_Name(String Last_Name){
        this.Last_Name = Last_Name;
    }
    public String getID_Number(){
        return ID_Number;
    }
    public void setID_Number(String ID_Number){
        this.ID_Number =ID_Number;
    }
    public String getResidence_Address(){
        return Residence_Address;
    }
    public  void setResidence_Address(String Residence_address){
        this.Residence_Address = Residence_address;
    }
    public String getMonthly_Earnings(){
        return Monthly_Earnings;
    }
    public void setMonthly_Earnings(String Monthly_Earnings){
        this.Monthly_Earnings = Monthly_Earnings;
    }
    public String getMonthly_Expenditure(){
        return Monthly_Expenditure;
    }
    public void setMonthly_Expenditure(String Monthly_Expenditure){
        this.Monthly_Expenditure = Monthly_Expenditure;
    }
}
