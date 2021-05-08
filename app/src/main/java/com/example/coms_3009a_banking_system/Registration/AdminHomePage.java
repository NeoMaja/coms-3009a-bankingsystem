package com.example.coms_3009a_banking_system.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.VerificationActivity;

public class AdminHomePage extends AppCompatActivity {

    public CardView mVcard, mPcard;
    Intent move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        mVcard =(CardView) findViewById(R.id.verifiedCardID);
        mPcard = (CardView) findViewById(R.id.pendingCardID);

        /*mPcard.setOnClickListener(v -> {
            move = new Intent(getApplicationContext(),ClientsActivity.class);
            startActivity(move);
        });*/

        mVcard.setOnClickListener(v -> {
            move = new Intent(getApplicationContext(), VerificationActivity.class);
            startActivity(move);
        });
    }
}