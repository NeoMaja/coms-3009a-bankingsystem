package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Profile;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Transact;
import com.example.coms_3009a_banking_system.login.Login2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Transfer extends AppCompatActivity {

    // initializing variables
    EditText transferAmount,transferDate;
    Button transfer;

    RadioButton radioButton1, radioButton2;
    RadioGroup radioGroupfrom;
    RadioGroup radioGroupTo;

    String From;
    String Amount;
    String To;
    String ref;
    private String Email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer3);

        // assigning variables
        transferAmount = (EditText)findViewById(R.id.transferAmount);
        transferDate = (EditText)findViewById(R.id.transferDate);
        radioGroupfrom = (RadioGroup)findViewById(R.id.transferRadioGroup_From);
        radioGroupTo = (RadioGroup)findViewById(R.id.transferRadioGroup_To);
        transfer = (Button)findViewById(R.id.transfer);

        SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        String date = dateF.format(Calendar.getInstance().getTime());

        transferDate.setText(date);

        //Get Email from previous page
        Intent intent = getIntent();
        Email = intent.getStringExtra("email");
        password= intent.getStringExtra("password");

        Log.e("Transfer",Email);



        //website to post to the php file
        //https://lamp.ms.wits.ac.za/home/s2143686/Account_activity.php

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Amount = transferAmount.getText().toString();

                //get selected radio button From
                int selected = radioGroupfrom.getCheckedRadioButtonId();
                radioButton1 = (RadioButton) findViewById(selected);
                From = radioButton1.getText().toString();

                //get selected radio button To
                int selected2 = radioGroupTo.getCheckedRadioButtonId();
                radioButton2 = (RadioButton) findViewById(selected2);
                To = radioButton2.getText().toString();

                ContentValues parameters = new ContentValues();
                parameters.put("Acc_Name", From);
                parameters.put("Pin", 12345);
                parameters.put("Activity", "Transfer");
                parameters.put("Amount", Amount);
                parameters.put("To", To);
                parameters.put("Email", Email);
                parameters.put("Ref","Transfer");

                AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Account_activity.php",parameters){
                    @Override
                    protected void onPostExecute(String output) {
                        //Do the transfer and toast output
                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();

                    }
                };
                asyncHttpPost.execute();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Pressing back from Transact Page takes you to client account page
        Intent intent1 = new Intent(getApplicationContext(), Transact.class);
        intent1.putExtra("email",Email);
//        intent1.putExtra("password",password);
        startActivity(intent1);
        finish();
        overridePendingTransition(0,0);
    }

    public void onRadioButtonClicked(View view) {
    }
}