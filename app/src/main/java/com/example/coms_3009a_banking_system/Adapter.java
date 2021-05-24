package com.example.coms_3009a_banking_system;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
     private Context mCtx;
     private List<User> userList;
     private static final String URL = "https://lamp.ms.wits.ac.za/home/s2143686/Bank_Accept.php";
    private static final String URL1 = "https://lamp.ms.wits.ac.za/home/s2143686/Bank_Reject.php";

     public Adapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

         TextView mName;
         TextView mID;
         TextView mEmail;
         TextView mPhone;
         Button btnAccept, btnReject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.title);
            mID = itemView.findViewById(R.id.id_subtitle);
            mEmail = itemView.findViewById(R.id.email_subtitle);
            mPhone = itemView.findViewById(R.id.no_subtitle);
            btnAccept = itemView.findViewById(R.id.btnaccept);
            btnReject = itemView.findViewById(R.id.btnreject);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflates cardview
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user,null);
        //Returns view to Holder class
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userList.get(position);

        holder.mName.setText(user.getName());
        holder.mID.setText(user.getId());
        holder.mEmail.setText(user.getEmail());
        holder.mPhone.setText(user.getPhoneNo());

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Confirm Reject");
                builder.setMessage("Do you reject " + user.getName());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, URL1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);


                                    //Delete(position);
                                    if (jsonObject.names().get(0).equals("success")) {

                                        Toast.makeText(mCtx.getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(mCtx.getApplicationContext(), "ERROR " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("name", user.getName());
                                hashMap.put("Email", user.getEmail());
                                return hashMap;


                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                        requestQueue.add(request);


                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Confirm Accept");
                builder.setMessage("Do you accept " + user.getName());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);


                                    //Delete(position);
                                    if (jsonObject.names().get(0).equals("success")) {

                                        Toast.makeText(mCtx.getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(mCtx.getApplicationContext(), "ERROR " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> hashMap = new HashMap<String, String>();
                                //Intent intent = new Intent();
                                //String email =intent.getStringExtra("email");
                                //String name =intent.getStringExtra("name");
                                hashMap.put("Id", user.getId());

//                        hashMap.put("First_Name", user.getName());

                                return hashMap;


                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                        requestQueue.add(request);


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void Delete(int position)
    {
        userList.remove(position);
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
