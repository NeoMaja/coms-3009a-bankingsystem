package com.example.coms_3009a_banking_system.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.coms_3009a_banking_system.R;

public class AdminKeyView extends AppCompatActivity {

    //getting admin key from AdminRegister to be displayed so Admin can see it.
    String Admin_Key = getIntent().getStringExtra("Admin_Key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_key_view2);
    }
}