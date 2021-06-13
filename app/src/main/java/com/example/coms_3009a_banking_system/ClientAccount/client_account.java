package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AdminProfilePage;
import com.example.coms_3009a_banking_system.AsyncHTTPPost;

import com.example.coms_3009a_banking_system.ClientsActivity;
import com.example.coms_3009a_banking_system.Profile;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Transact;
import com.example.coms_3009a_banking_system.login.Login2;
import com.example.coms_3009a_banking_system.login.login;
import com.example.coms_3009a_banking_system.usertype;
import com.example.coms_3009a_banking_system.VerificationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class client_account extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Clientitem> mClientList;
    private ClientAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;;

    String AccountType;
    String AccountNumber;
    String Amount;
    String email;
    String password;

    Button logout;
    Button credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_account);

        // get email and password via intent

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password =intent.getStringExtra(" password");


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.acc);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent1;
                switch (item.getItemId()){

                    case R.id.acc:
                        return true;

                    case R.id.button_profile:
                        intent1 = new Intent(client_account.this, Profile.class);
                        intent1.putExtra("email", email);
                        intent1.putExtra("password", password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_transact:
                        intent1 = new Intent(client_account.this, Transact.class);
                        intent1.putExtra("email", email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_insert:
                        intent1 = new Intent(client_account.this, Cli_Acc_Test.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_logoutnow:
                        intent1 = new Intent(client_account.this, Login2.class);
                        intent1.putExtra("email", email);
                        intent1.putExtra("password", password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        ContentValues cv = new ContentValues();
        cv.put("Email", email);
        getAccounts(cv);
    }

    private void getAccounts(ContentValues cv){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Cli_Account_data.php", cv){
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray array = new JSONArray(output);
                    ArrayList<Clientitem> cl = new ArrayList<>();
                    Clientitem clientitem;
                    for(int i =0; i < array.length(); i++){
                        final JSONObject object = (JSONObject) array.get(i);
                        AccountType = object.getString("Account_Type_Name");
                        AccountNumber = object.getString("Account_Number");
                        Amount = object.getString("Balance");

                        clientitem = new Clientitem(AccountType, AccountNumber, Amount);
                        cl.add(clientitem);
                        mClientList = cl;

                        adapter = new ClientAdapter(mClientList);
                        recyclerView.setAdapter(adapter);
                    }

                    /*if (mClientList.size() == 0){
                        Toast toast = Toast.makeText(client_account.this, "User has no accounts", Toast.LENGTH_LONG);
                    }else{
                        adapter = new ClientAdapter(mClientList);
                        recyclerView.setAdapter(adapter);
                    }*/
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}