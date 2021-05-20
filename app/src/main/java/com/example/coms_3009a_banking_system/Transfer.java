package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class Transfer extends AppCompatActivity {

    // initializing variables
    EditText transferAmount,transferDate;
    Button transfer;
    RadioButton fromChequeAccount, fromInvestmentAccount, fromSavingsAccount,
            toChequeAccount, toInvestmentAccount, toSavingsAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer3);

        // assigning variables
        transferAmount = findViewById(R.id.transferAmount);
        transferDate = findViewById(R.id.transferDate);

        fromChequeAccount = (RadioButton)findViewById(R.id.fromChequeAccount);
        fromInvestmentAccount = (RadioButton)findViewById(R.id.fromInvestmentAccount);
        fromSavingsAccount = (RadioButton)findViewById(R.id.fromSavingsAccount);

        toChequeAccount = (RadioButton)findViewById(R.id.toChequeAccount);
        toInvestmentAccount = (RadioButton)findViewById(R.id.toInvestmentAccount);
        toSavingsAccount = (RadioButton)findViewById(R.id.toSavingsAccount);

        transfer = (Button)findViewById(R.id.transfer);

    }

    public void onRadioButtonClicked(View view) {
    }
}