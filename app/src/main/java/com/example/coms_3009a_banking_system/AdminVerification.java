package com.example.coms_3009a_banking_system;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coms_3009a_banking_system.login.Login2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminVerification extends AppCompatActivity {
    //declaring variable
    private String email;
    private String password;
    private RecyclerView mRecycleview;
    private CreditAdapter madapt1;

    //a list to store all the requests
    private List<CreditClass> userList;

    //this is the JSON Data URL
    private static final String URL_Data = "https://lamp.ms.wits.ac.za/home/s2143686/Credit_Data.php";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        // moving to verification activity
        //get password and email via intent
        Intent getIntent= getIntent();
        email = getIntent.getStringExtra("email");
        password = getIntent.getStringExtra("password");
//        Log.e("Home email ", email);
//        Log.e("Home Password",password);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.home:
                        return true;

                    case R.id.pending_users:
                        intent = new Intent(getApplicationContext(),VerificationActivity.class);
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verified_users:
                        intent = new Intent(getApplicationContext(),ClientsActivity.class);
                        //put email and password in intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.admin_profile:
                        intent = new Intent(getApplicationContext(),AdminProfilePage.class);
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.admin_logout:
                        intent = new Intent(getApplicationContext(), Login2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //  intent1.putExtra("EXIT", true);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        mRecycleview = findViewById(R.id.recycler_View3);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();

        getData();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //If back pressed then Logout
       Intent intent = new Intent(getApplicationContext(), Login2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //  intent1.putExtra("EXIT", true);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray users = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < users.length(); i++) {

                                //getting order object from json array
                                JSONObject user = users.getJSONObject(i);

                                String ID_Number = user.getString("ID_Number");
                                String First_Name = user.getString("First_Name");
                                String Last_Name = user.getString("Last_Name");
                                String Residence_Address = user.getString("Residence_Address");
                                String Monthly_Earnings = user.getString("Monthly_Earnings");
                                String Monthly_Expenditure = user.getString("Monthly_Expenditure" +
                                        "");
                                CreditClass userlist = new CreditClass(ID_Number, First_Name, Last_Name, Residence_Address, Monthly_Earnings, Monthly_Expenditure);
                                userList.add(userlist);

                            }

                            //creating adapter object and setting it to recyclerview
                            madapt1 = new CreditAdapter(AdminVerification.this,userList);
                            mRecycleview.setAdapter(madapt1);

                        } catch (JSONException jsonException) {

                            jsonException.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
