package com.example.coms_3009a_banking_system.Registration;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.MainActivity;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.client_account;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class clientRegister extends AppCompatActivity {

    //initializing variables
    EditText firstName,lastName, username,idNumber,celNumber,email,password,confirmPassword;
    Button register;
    String Cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.client_reg_toolbar);

//        assigning variables

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        username = (TextInputEditText) findViewById(R.id.username);
        idNumber = findViewById(R.id.idNumber);
        celNumber = findViewById(R.id.cellNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        register = findViewById(R.id._register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoRegister();

            }
        });
    }

    //        Validation function
    private void validateDataAndDoRegister() {
        String F_Name = firstName.getText().toString().trim();
        String L_Name = lastName.getText().toString().trim();
        String Username = username.getText().toString().trim();
        String ID = idNumber.getText().toString().trim();
        String Cell = celNumber.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String c_password = confirmPassword.getText().toString().trim();


//            Fname
        Pattern F_P = Pattern.compile("^[a-zA-Z]+$");
        Matcher Fm = F_P.matcher(F_Name);
        boolean f =Fm.matches();

//            Lname check check
        Pattern L_P = Pattern.compile("^[a-zA-Z]+$");
        Matcher Lm = L_P.matcher(L_Name);
        boolean l =Lm.matches();

//            ID check
        Pattern ID_P = Pattern.compile("^[0-9]{13}$");
        Matcher IDm = ID_P.matcher(ID);
        boolean i = IDm.matches();
//            Cell check
        Pattern Cell_P = Pattern.compile("^[0-9]{10}$");
        Matcher Cm = Cell_P.matcher(Cell);
        boolean c = Cm.matches();

//            Email check
        Pattern E_P = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher Em = E_P.matcher(Email);
        boolean e = Em.matches();

//            Password Check
        Pattern Pass_P = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$");
        Matcher Pm = Pass_P.matcher(Password);
        boolean p = Pm.matches();


        String assistance = null;

        if (!l) {
            firstName.setError("Enter a valid First Name");
            firstName.requestFocus();
        }
        else if (!f) {
            lastName.setError("Enter a valid Last Name");
            lastName.requestFocus();
        }

        else if(Username.length()<5){
            username.setError("Enter 5 character Username");
            username.requestFocus();
        }
        else if(!i){
            idNumber.setError("Enter 13 digit ID number");
            idNumber.requestFocus();
        }

        else if (!c){
            celNumber.setError("Enter 10 digit Cellphone Number");
            celNumber.requestFocus();
        }
        else if(!e){
            email.setError("Enter valid email address");
            email.requestFocus();
        }

        else if (Password.isEmpty()){
            password.setError("Enter password");
            password.requestFocus();
        }else if(!p){
            password.setError("Password must be 8 characters with at least 1 Upper case , 1 lower case and 1 special Character");
            password.requestFocus();
        }

        else if (c_password.isEmpty()) {
            confirmPassword.setError("enter password");
            confirmPassword.requestFocus();
        }
        else if (!Password.equals(c_password)){
            password.setError("Passwords do not match");
            password.requestFocus();
            confirmPassword.setError("Passwords do not match");
            confirmPassword.requestFocus();
            password.setText("");
            confirmPassword.setText("");
        }
        else {
            doRegister(F_Name,L_Name,Username,ID, Email, Password);
        }
    }




    private void doRegister(String fName,String lName ,String username, String IdNumber,String email, String password) {
//        OkHttpClient client = new OkHttpClient();
/////////////////////////////////Meng's DATABASE///////////////////////////////////////////////////////////////////////////
//
////By Lindo, please dont touch (21 April 2021)
////Registering into the system

        //add information to database
        //adding/declaring parameters
        ContentValues parameters = new ContentValues();
        parameters.put("ID_Number",IdNumber );
        parameters.put("Username", username );
        parameters.put("First_Name", fName );
        parameters.put("LastName", lName);
        parameters.put("Password", password);
        parameters.put("Email", email );
        parameters.put("Cellphone_Number", Cell);
        parameters.put("User_Type_ID","250");           //TODO


        //TODO
        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Bank_Registration.php",parameters){
            @Override
            protected void onPostExecute(String output) {
                //check for existing username error

                Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();

                if((output.equals("Await your verification"))) {
                    //Go to login page after successful registration.
                    Toast.makeText(getApplicationContext(),"Welcome to the family",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(clientRegister.this, client_account.class);
                    startActivity(intent);
                }
            }
        };
        asyncHttpPost.execute();
    }

}

