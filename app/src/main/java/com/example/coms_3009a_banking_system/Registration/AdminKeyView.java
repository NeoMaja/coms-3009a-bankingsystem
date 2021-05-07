package com.example.coms_3009a_banking_system.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coms_3009a_banking_system.R;

public class AdminKeyView extends AppCompatActivity {

    Button Confirm =findViewById(R.id._confirmKey);

    //getting admin key from AdminRegister to be displayed so Admin can see it.
    String Admin_Key = getIntent().getStringExtra("Admin_Key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_key_view2);


        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoRegister();

            }
        });
    }


    private void validateDataAndDoRegister() {

        if
    }


}