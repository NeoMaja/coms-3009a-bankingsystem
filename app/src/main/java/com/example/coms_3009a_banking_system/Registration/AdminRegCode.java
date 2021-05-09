package com.example.coms_3009a_banking_system.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coms_3009a_banking_system.R;

public class AdminRegCode extends AppCompatActivity {

    EditText Code;
    Button Enter;
    String RegCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_code);

        Code = (EditText)findViewById(R.id.AdRegCode);
        Enter = (Button)findViewById(R.id.CodeButton);
        RegCode = Code.getText().toString();


        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(RegCode == "201221"){
                    Intent intent = new Intent(AdminRegCode.this, adminRegister.class);
                    startActivity(intent);
                }

            }
        });



    }
}