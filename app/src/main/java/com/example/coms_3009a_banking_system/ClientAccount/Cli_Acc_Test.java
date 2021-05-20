package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Client_Verification_Status;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.login.AdminLoginKey;

public class Cli_Acc_Test extends AppCompatActivity {

    //this code is to create an account for a client.

    Button submit;
    String Acc_Type;
    RadioButton radioButton;
    RadioGroup account_type;
    String email;

    EditText GetPin;
    EditText GetVerPin;
    String Pin;
    String VerPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_acc_test);

        submit = (Button)findViewById(R.id.verifyaccty);
        account_type = (RadioGroup)findViewById(R.id.account_type);
        GetPin = (EditText)findViewById(R.id.PinAcc);
        GetVerPin = (EditText)findViewById(R.id.verPin);

        //getting email from previous page
        email = getIntent().getStringExtra("email");

        //Try to get data from Radio Group

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the pin and verify if they match
                Pin = GetPin.getText().toString();
                VerPin = GetVerPin.getText().toString();

                if(Pin.equals("") || VerPin.equals("")) {
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_SHORT).show();
                }
                    else{

                        if (Pin.equals(VerPin)) {

                            //get selected radio button
                            int selected = account_type.getCheckedRadioButtonId();
                            radioButton = (RadioButton) findViewById(selected);
                            Acc_Type = radioButton.getText().toString();

                            //send Acc_Type and email to database to create new account

                            ContentValues parameters = new ContentValues();
                            parameters.put("Email", email);
                            parameters.put("Acc_Type", Acc_Type);
                            parameters.put("Pin", Pin);

                            AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Create_account2.php", parameters) {
                                @Override
                                protected void onPostExecute(String output) {
                                    //if output = success then go the activiy page
                                    if (output.equals("success")) {
                                        Intent intent = new Intent(Cli_Acc_Test.this, client_account.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            };
                            asyncHttpPost.execute();

                        }
                    }
            }
        });



    }
}