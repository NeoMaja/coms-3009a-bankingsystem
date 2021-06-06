package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coms_3009a_banking_system.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientApplication extends AppCompatActivity {

    // initializing variables
    EditText Name,Surname,Residence,IDNumber,Earnings,Expenditure;
    Button apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_application);

        // assigning variables

        Name = findViewById(R.id.Name);
        Surname = findViewById(R.id.Surname);
        Residence = findViewById(R.id.residence_address);
        IDNumber = findViewById(R.id.idNumber);
        Earnings = findViewById(R.id.monthly_earnings);
        Expenditure= findViewById(R.id.monthly_expenditure);

        apply = findViewById(R.id._apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoApplication();
            }
        });

    }

    private void validateDataAndDoApplication() {

        String CName = Name.getText().toString().trim();
        String CSurname = Surname.getText().toString().trim();
        String CResidence = Residence.getText().toString().trim();
        String CID = IDNumber.getText().toString().trim();
        String CEarnings = Earnings.getText().toString().trim();
        String CExpenditure = Expenditure.getText().toString().trim();

        // Name
        Pattern F_P = Pattern.compile("^[a-zA-Z]+$");
        Matcher Fm = F_P.matcher(CName);
        boolean f =Fm.matches();

        // Surname
        Pattern L_P = Pattern.compile("^[a-zA-Z]+$");
        Matcher Lm = L_P.matcher(CSurname);
        boolean s =Lm.matches();

        // ID check
        Pattern ID_P = Pattern.compile("^[0-9]{13}$");
        Matcher IDm = ID_P.matcher(CID);
        boolean i = IDm.matches();

        if (!f) {
            Name.setError("Enter a valid name");
            Name.requestFocus();
        }
        else if (!s) {
            Surname.setError("Enter a valid surname");
            Surname.requestFocus();
        }

        else if(Residence.length()<5){
            Residence.setError("Enter residence address");
            Residence.requestFocus();
        }
        else if(!i){
            IDNumber.setError("Enter 13 digit ID number");
            IDNumber.requestFocus();
        }

        else if (Earnings.length()<1){
            Earnings.setError("Enter monthly earnings");
            Earnings.requestFocus();

        }else if(Expenditure.length()<1){
            Expenditure.setError("Enter monthly expenditure");
            Expenditure.requestFocus();

        }
        else {
            doApplication(CName, CSurname, CResidence, CID, CEarnings, CExpenditure);
        }
    }

    private void doApplication(String name, String surname, String address, String id, String earnings, String expenditure){
        // do something
    }
}