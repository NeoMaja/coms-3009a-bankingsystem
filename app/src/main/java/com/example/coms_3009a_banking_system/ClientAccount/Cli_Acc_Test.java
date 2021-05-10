package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Client_Verification_Status;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.client_account;
import com.example.coms_3009a_banking_system.login.AdminLoginKey;

public class Cli_Acc_Test extends AppCompatActivity {

    //this code is to create an account for a client.

    Button submit;
    String Acc_Type;
    RadioButton radioButton;
    RadioGroup account_type;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cli_acc_test);

        submit = (Button)findViewById(R.id.verifyaccty);
        account_type = (RadioGroup)findViewById(R.id.account_type);

        //getting email from previous page
        Email = getIntent().getStringExtra("email");

        //Try to get data from Radio Group

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get selected radio button
                int selected = account_type.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selected);
                Acc_Type = radioButton.getText().toString();

            }
        });

        //send Acc_Type and email to database to create new account

        ContentValues parameters = new ContentValues();
        parameters.put("Email", Email);
        parameters.put("Acc_Type", Acc_Type);

        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Create_account2.php",parameters){
            @Override
            protected void onPostExecute(String output) {
                //if output = success then go the activiy page
                if(output.equals("success")){

                    Intent intent = new Intent(Cli_Acc_Test.this, client_account.class);
                    startActivity(intent);
                }

            }
        };
        asyncHttpPost.execute();

    }
}