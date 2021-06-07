package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.coms_3009a_banking_system.login.Login2;

public class AdminWelcomePage extends AppCompatActivity {

    String Email;
    String Password;

    private static int TIME_OUT = 4000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        Email = getIntent().getStringExtra("email");
        Password = getIntent().getStringExtra("password");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(AdminWelcomePage.this, AdminVerification.class);
                i.putExtra("email",Email);
                i.putExtra("password",Password);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

    }
}