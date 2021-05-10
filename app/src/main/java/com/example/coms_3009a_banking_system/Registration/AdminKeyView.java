package com.example.coms_3009a_banking_system.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.login.Login2;

public class AdminKeyView extends AppCompatActivity {

    TextView AdKey;

    //getting admin key from AdminRegister to be displayed so Admin can see it.
    String Admin_Key;
    Button _Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_key_view2);

        Admin_Key = getIntent().getStringExtra("Admin_Key");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Admin Key Is :");

        AdKey = (TextView)findViewById(R.id.AdKView);
        _Continue = (Button)findViewById(R.id.Continue);
        AdKey.setText(Admin_Key);


        _Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminKeyView.this, Login2.class);
                startActivity(intent);

            }
        });

    }


}