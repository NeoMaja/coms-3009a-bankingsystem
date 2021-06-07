package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Profile;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Registration.clientRegister;
import com.example.coms_3009a_banking_system.Transact;
import com.example.coms_3009a_banking_system.login.Login2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientApplication extends AppCompatActivity {

    // initializing variables
    EditText Name,Surname,Residence,IDNumber,Earnings,Expenditure;
    Button apply;
    String Email, password;

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
                Toast.makeText(getApplicationContext(),"Application Complete",Toast.LENGTH_SHORT).show();
            }
        });

        //Get Email from previous page
        Intent intent = getIntent();
        Email = intent.getStringExtra("email");
        password= intent.getStringExtra("password");

       /* BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.acc);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent1;
                switch (item.getItemId()){

                    case R.id.acc:
                        return true;

                    case R.id.button_logoutnow:
                        intent1 = new Intent(ClientApplication.this, Login2.class);
                        intent1.putExtra("email",Email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_transact:
                        intent1 = new Intent(ClientApplication.this, Transact.class);
                        intent1.putExtra("email", Email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_insert:
                        intent1 = new Intent(ClientApplication.this, Cli_Acc_Test.class);
                        intent1.putExtra("email",Email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_profile:
                        intent1 = new Intent(ClientApplication.this, Profile.class);
                        intent1.putExtra("email", Email);
                        intent1.putExtra("password", password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
*/
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
        Name.getText().clear();
        Surname.getText().clear();
        Residence.getText().clear();
        IDNumber.getText().clear();
        Earnings.getText().clear();
        Expenditure.getText().clear();

        //add information to database
        //adding/declaring parameters

//        ContentValues parameters = new ContentValues();
//        parameters.put("Name", String.valueOf(Name));
//        parameters.put("Surname", String.valueOf(Surname));
//        parameters.put("Residence", String.valueOf(Residence));
//        parameters.put("IDNumber", String.valueOf(IDNumber));
//        parameters.put("Earnings", String.valueOf(Earnings));
//        parameters.put("Expenditure", String.valueOf(Expenditure));        //y
//
//
//
//        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Bank_Registration.php",parameters){
//            @Override
//            protected void onPostExecute(String output) {
//
//                Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();
//
//                if((output.equals("Await your verification"))) {
//                    // Display Application Complete
//                    Toast.makeText(getApplicationContext(),"Application Complete",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        asyncHttpPost.execute();
  }
}