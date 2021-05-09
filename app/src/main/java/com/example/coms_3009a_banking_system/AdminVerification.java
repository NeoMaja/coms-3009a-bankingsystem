package com.example.coms_3009a_banking_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminVerification extends AppCompatActivity {

    private CardView mVerified, mPending;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        mPending = findViewById(R.id.pendingCardID);
        mVerified = findViewById(R.id.verifiedCardID);

        mVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity_2();
            }
        });
        mPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();
            }
        });
    }

    public void OpenActivity(){
        Intent intent = new Intent(this,VerificationActivity.class);
        startActivity(intent);
    }

    public void OpenActivity_2(){
        Intent intent = new Intent(this,ClientsActivity.class);
        startActivity(intent);
    }

}
