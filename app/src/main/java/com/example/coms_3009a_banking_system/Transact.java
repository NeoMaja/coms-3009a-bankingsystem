package com.example.coms_3009a_banking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.coms_3009a_banking_system.ClientAccount.Cli_Acc_Test;
import com.example.coms_3009a_banking_system.ClientAccount.ClientAdapter;
import com.example.coms_3009a_banking_system.ClientAccount.ClientApplication;
import com.example.coms_3009a_banking_system.ClientAccount.Clientitem;
import com.example.coms_3009a_banking_system.ClientAccount.Pay;
import com.example.coms_3009a_banking_system.ClientAccount.Transfer;
import com.example.coms_3009a_banking_system.ClientAccount.client_account;
import com.example.coms_3009a_banking_system.login.Login2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Transact extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Clientitem> mClientList;
    private ClientAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;;

    String AccountType;
    String AccountNumber;
    String Amount;
    String email;
    String password;

    Button pay,transfer, credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transact);

        //get Intent
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password =intent.getStringExtra(" password");

        pay = (Button)findViewById(R.id.button_PAYSCREEN);

        // SetNavigationBar
        setNavigationBar();

        //setButtons
        setButtons();


    }

    private void setButtons() {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payIntent = new Intent(Transact.this,
                        Pay.class);
                payIntent.putExtra("email",email);
                payIntent.putExtra("password",password);
                startActivity(payIntent);
            }
        });

        transfer = (Button)findViewById(R.id.button_TRANSFERSCREEN);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferIntent = new Intent(Transact.this,
                        Transfer.class);
                transferIntent.putExtra("email",email);
                transferIntent.putExtra("password",password);
                startActivity(transferIntent);
            }
        });

        credit = (Button)findViewById(R.id.button_CREDITSCREEN);

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditIntent = new Intent(Transact.this,
                        ClientApplication.class);
                creditIntent.putExtra("email",email);
                creditIntent.putExtra("password",password);
                startActivity(creditIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reset Navigation Bar
        setNavigationBar();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Pressing back from Transact Page takes you to client account page
        Intent intent1 = new Intent(getApplicationContext(), client_account.class);
        intent1.putExtra("email",email);
        intent1.putExtra("password",password);
        startActivity(intent1);
        finish();
        overridePendingTransition(0,0);
    }

    private void setNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.button_transact);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent1;
                switch (item.getItemId()){

                    case R.id.acc:
                        intent1 = new Intent(Transact.this,client_account.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_logoutnow:
                        // Logout of the App
                        intent1 = new Intent(getApplicationContext(), Login2.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //  intent1.putExtra("EXIT", true);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_transact:
                        // We Are Already in the transact Class so we do nothing
                        return true;

                    case R.id.button_insert:
                        intent1 = new Intent(Transact.this, Cli_Acc_Test.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_profile:
                        intent1 = new Intent(Transact.this, Profile.class);
                        intent1.putExtra("email", email);
                        intent1.putExtra("password", password);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
}