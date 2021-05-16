package com.example.coms_3009a_banking_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdminVerification extends AppCompatActivity { //declaring variable



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page); // moving to verification activity

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);


        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.pending_users:
                        startActivity(new Intent(getApplicationContext()
                                ,VerificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verified_users:
                        startActivity(new Intent(getApplicationContext()
                                ,ClientsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }



}
