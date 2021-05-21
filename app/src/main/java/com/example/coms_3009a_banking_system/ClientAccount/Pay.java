package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.coms_3009a_banking_system.R;

public class Pay extends AppCompatActivity {

    // initializing variables
    EditText recipient,amount,reference,paymentDate;
    Button pay;
    RadioButton bankA, bankB, bankC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // assigning variables
        recipient = findViewById (R.id.recipient);
        amount = findViewById(R.id.amount);
        reference = findViewById(R.id.reference);
        paymentDate = findViewById(R.id.paymentDate);

        bankA = (RadioButton)findViewById(R.id.toBankA);
        bankB = (RadioButton)findViewById(R.id.toBankB);
        bankC = (RadioButton)findViewById(R.id.toBankC);

        pay = (Button)findViewById(R.id.pay);

        //website to post to the php file
        //https://lamp.ms.wits.ac.za/home/s2143686/Account_activity.php

    }

    public void onRadioButtonClicked(View view) {
    }
}