package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usertype extends AppCompatActivity {

    Button Admin;
    Button Patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);

        Admin = (Button) findViewById(R.id.Admin_id);
        Patient = (Button) findViewById(R.id.client_id);

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(usertype.this, adminRegister.class);
                startActivity(registerIntent);
            }

        });
        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntentP = new Intent(usertype.this, clientRegister.class);
                startActivity(registerIntentP);
            }

        });
    }
}
