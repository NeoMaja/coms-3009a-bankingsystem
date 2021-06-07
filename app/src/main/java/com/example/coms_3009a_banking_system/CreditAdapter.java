package com.example.coms_3009a_banking_system;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.mViewHolder> {

    private Context mC;
    private List<CreditClass> creditClassList;
    private static final String URL = "https://lamp.ms.wits.ac.za/home/s2143686/Credit_Approval.php";
    private static final String URL1 = "https://lamp.ms.wits.ac.za/home/s2143686/Credit_Reject.php";

    public CreditAdapter(Context mC, List<CreditClass> creditClassList) {
        this.mC = mC;
        this.creditClassList = creditClassList;

    }

    public class mViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mSurname;
        TextView mID;
        TextView mResidence;
        TextView mEarn;
        TextView mExpend;
        Button btnAccept, btnReject;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.name_title);
            mSurname = itemView.findViewById(R.id.titleSur);
            mID = itemView.findViewById(R.id.id_title);
            mResidence = itemView.findViewById(R.id.residence_address);
            mEarn = itemView.findViewById(R.id.earnings_subtitle);
            mExpend = itemView.findViewById(R.id.exp_subtitle);
            btnAccept = itemView.findViewById(R.id.acceptBtn);
            btnReject = itemView.findViewById(R.id.rejectBtn);

        }
    }

    @NotNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflates cardview
        LayoutInflater inflater = LayoutInflater.from(mC);
        View view = inflater.inflate(R.layout.credit_client,null);
        //Returns view to Holder class
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        CreditClass user = creditClassList.get(position);

        holder.mName.setText(user.getFirst_Name());
        holder.mSurname.setText(user.getLast_Name());
        holder.mID.setText(user.getID_Number());
        holder.mResidence.setText(user.getResidence_Address());
        holder.mEarn.setText(user.getMonthly_Earnings());
        holder.mExpend.setText(user.getMonthly_Expenditure());

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mC);
                builder.setTitle("Confirm Reject");
                builder.setMessage("Do you reject " + user.getFirst_Name());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, URL1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    if (jsonObject.names().get(0).equals("success")) {
                                        Delete(position);
                                        Toast.makeText(mC.getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(mC.getApplicationContext(), "ERROR " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                                hashMap.put("name", user.getFirst_Name());
                                return hashMap;


                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(mC);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(mC);
                builder.setTitle("Confirm Accept");
                builder.setMessage("Do you accept " + user.getFirst_Name());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    if (jsonObject.names().get(0).equals("success")) {

                                        Toast.makeText(mC.getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(mC.getApplicationContext(), "ERROR " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                                //hashMap.put("email", user.getEmail());
                                hashMap.put("id", user.getID_Number());
                                //hashMap.put("First_Name", user.getName());

                                return hashMap;


                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(mC);
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
        creditClassList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount() {
        return creditClassList.size();
    }
}
