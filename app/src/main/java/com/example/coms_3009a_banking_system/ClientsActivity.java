package com.example.coms_3009a_banking_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientsActivity extends AppCompatActivity {

    private RecyclerView mRecycleview;

    private ClientsAdapter madapt;

    //a list to store all the requests
    private List<ClientUser> userList;

    //this is    the JSON Data URL
    private static final String URL_Data = "https://lamp.ms.wits.ac.za/home/s2143686/Bank_ClientData.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients_verified);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.verified_users);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.verified_users:
                        return true;

                    case R.id.pending_users:
                        startActivity(new Intent(getApplicationContext()
                                ,VerificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        mRecycleview = findViewById(R.id.recycler_View2);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();

        getData();

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
                                String Email = user.getString("Email");
                                String Cellphone_Number = user.getString("Cellphone_Number");
                                ClientUser userlist = new ClientUser(ID_Number, First_Name, Email, Cellphone_Number);
                                userList.add(userlist);

                            }

                            //creating adapter object and setting it to recyclerview
                            madapt = new ClientsAdapter(ClientsActivity.this,userList);
                            mRecycleview.setAdapter(madapt);

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
