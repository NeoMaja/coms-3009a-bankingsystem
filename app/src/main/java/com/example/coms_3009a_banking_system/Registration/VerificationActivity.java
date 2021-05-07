package com.example.coms_3009a_banking_system.Registration;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coms_3009a_banking_system.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VerificationActivity extends AppCompatActivity {

    private RecyclerView mRecycleview;
    private RecyclerView.Adapter madapt;
    //a list to store all the requests
    private List<Model> model;
    private RecyclerView.LayoutManager manager;
    //this is    the JSON Data URL
    private static final String URL_Data = "https://lamp.ms.wits.ac.za/home/s2143686/Bank_Data.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        mRecycleview = findViewById(R.id.recycleView1);
        manager = new GridLayoutManager(VerificationActivity.this,2);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mRecycleview.setAdapter(madapt);
        model = new ArrayList<>();

        getData();

    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Data,
                response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = null;
                        array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting order object from json array
                            JSONObject order = array.getJSONObject(i);

                            String ID_Number =order.getString("ID_Number");
                            String First_Name = order.getString("First_Name");
                            String Email =order.getString("Email");
                            String Cellphone_Number = order.getString("Cellphone_Number");
                            String Image = order.getString("Image");

                            Model models = new Model(ID_Number,First_Name,Email,Cellphone_Number,Image);
                            model.add(models);
                        }

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                    //creating adapter object and setting it to recyclerview
                    madapt= new Adapter(VerificationActivity.this, model);
                    mRecycleview.setAdapter(madapt);

                },
                error -> {

                });

                //adding our stringrequest to queue
                Volley.newRequestQueue(VerificationActivity.this).add(stringRequest);
            }
}
