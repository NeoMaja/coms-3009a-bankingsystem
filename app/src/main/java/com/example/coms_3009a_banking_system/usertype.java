package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coms_3009a_banking_system.Registration.AdminRegCode;
import com.example.coms_3009a_banking_system.Registration.adminRegister;
import com.example.coms_3009a_banking_system.Registration.clientRegister;

public class usertype extends AppCompatActivity {

    Button Admin;
    Button Client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Type");

        Admin = (Button) findViewById(R.id.Admin_b);
        Client = (Button) findViewById(R.id.client_b);

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdminRegister = new Intent(usertype.this, AdminRegCode.class);
                startActivity(AdminRegister);
            }

        });
        Client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ClientRegister = new Intent(usertype.this, clientRegister.class);
                startActivity(ClientRegister);
            }

        });
    }
}
