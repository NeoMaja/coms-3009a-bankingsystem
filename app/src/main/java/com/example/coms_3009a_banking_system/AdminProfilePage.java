package com.example.coms_3009a_banking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.login.Login2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdminProfilePage extends AppCompatActivity {
    private  String email;
    private String password;

    private TextView FirstName;
    private TextView LastName;
    private TextView UserName;
    private TextView Email;
    private TextView P_Number;
    private TextView Admin_Key;
    private static final String TAG = "Profile";


    private String newMonthEarn,newMonthExpend,newResidenceAddress;
    private Button btnUpdateEarn,btnUpdateExpend,btnUpdateAddress;

    private TextView MonEarn,MonEx, rAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_page);

        FirstName= (TextView)findViewById(R.id.firstname);
        LastName= (TextView)findViewById(R.id.lastname);
        UserName=  (TextView) findViewById(R.id.username);
        Email= (TextView) findViewById(R.id.useremail);
        P_Number = (TextView) findViewById(R.id.p_number);
        Admin_Key=(TextView)findViewById(R.id.admin_key);

        //Edit Buttons
        btnUpdateEarn =(Button)findViewById(R.id.UpdateEarn);
        btnUpdateExpend=(Button)findViewById(R.id.UpdateExpend);
        btnUpdateAddress=(Button)findViewById(R.id.UpdateAddress);

        //Additional data
        MonEarn = (TextView)findViewById(R.id.monEarn);
        MonEx = (TextView)findViewById(R.id.Expend);
        rAddress = (TextView)findViewById(R.id.profileAddress);



        // get email and password via intent
        Intent getIntent= getIntent();
        email = getIntent.getStringExtra("email");
        password = getIntent.getStringExtra("password");
        Log.e(TAG,email);
        Log.e(TAG,password);


        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/retrieveUserData.php").newBuilder(); //url here
        urlBuilder.addQueryParameter("email", email);
//        urlBuilder.addQueryParameter("password", password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {


            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();
                AdminProfilePage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jO = new JSONObject(responseData);
                            String first_name = jO.getString("First Name");
                            String last_name = jO.getString("Last Name");
                            String user_name =jO.getString("username");
                            String  _email = jO.getString("email");
                            String number = jO.getString("cellphone");
                            String A_key = jO.getString("A_key");

                            String MonthlyEarn =jO.getString("Monthly Earnings");
                            String MonthlyEx =jO.getString("Monthly Expenditure");
                            String ResAddress =jO.getString("Residence Address");


                            FirstName.setText(first_name);
                            UserName.setText(user_name.toUpperCase());
                            LastName.setText(last_name);
                            Email.setText(_email);
                            P_Number.setText(number);
                            Admin_Key.setText(A_key.toUpperCase());


                            MonEarn.setText(MonthlyEarn);
                            MonEx.setText(MonthlyEx);
                            rAddress.setText(ResAddress);


                            //  Toast.makeText(Profile.this, first_name, Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(AdminProfilePage.this, "Login  error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        //UpdateEarn button

        btnUpdateEarn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Update Monthly Earnings");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            builder.setView(input);
            builder.setCancelable(false);
            // Set up the buttons
            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newMonthEarn = input.getText().toString().trim();
                    if(newMonthEarn.equals(""))
                    {
                        input.setError("Required field!");
                    }
                    else
                    {
                        UpdateEarn(email,newMonthEarn,MonEarn);
                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });

//    Update Monthly expenditure button

        btnUpdateExpend.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Update Monthly Expenditure");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            builder.setView(input);
            builder.setCancelable(false);
            // Set up the buttons
            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newMonthExpend = input.getText().toString().trim();
                    if(newMonthExpend.equals(""))
                    {
                        input.setError("Required field!");
                    }
                    else
                    {
                        UpdateExpend(email,newMonthExpend,MonEx);
                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });

//Update Monthly expenditure


        btnUpdateAddress.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Update Residence Address");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            builder.setView(input);
            builder.setCancelable(false);
            // Set up the buttons
            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newResidenceAddress = input.getText().toString().trim();
                    if(newResidenceAddress.equals(""))
                    {
                        input.setError("Required field!");
                    }
                    else
                    {
                        UpdateAddress(email,newResidenceAddress,rAddress);
                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);


        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.admin_profile);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.admin_profile:
                        return true;

                    case R.id.home:
                        intent = new Intent(getApplicationContext(),AdminVerification.class);
                        // add email and password on intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        return true;

                    case R.id.pending_users:
                        intent = new Intent(getApplicationContext(),VerificationActivity.class);
                        //put email and password in intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verified_users:
                        intent = new Intent(getApplicationContext(),ClientsActivity.class);
                        //put email and password in intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.admin_logout:
                        intent = new Intent(getApplicationContext(), Login2.class);
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });


    }

    private void UpdateEarn(String email,String NewEarn,TextView MonEarn){

        ContentValues parameters = new ContentValues();
        parameters.put("Email", email );
        parameters.put("DetailID", "1");
        parameters.put("Detail",NewEarn);


        //TODO
        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/UpdateInfo.php",parameters){
            @Override
            protected void onPostExecute(String output) {

                Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();

                if((output.equals("Monthly Earnings updated"))) {
                    //Go to login page after successful registration.
                    Toast.makeText(getApplicationContext(),"Monthly Earnings updated",Toast.LENGTH_SHORT).show();
                    MonEarn.setText(NewEarn);

                }
            }
        };
        asyncHttpPost.execute();
    }



    private void UpdateExpend(String email,String newMonthExpend,TextView MonEx){

        ContentValues parameters = new ContentValues();
        parameters.put("Email", email );
        parameters.put("DetailID", "2");
        parameters.put("Detail",newMonthExpend);


        //TODO
        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/UpdateInfo.php",parameters){
            @Override
            protected void onPostExecute(String output) {

                Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();

                if((output.equals("Monthly Expenditure updated"))) {
                    //Go to login page after successful registration.
                    Toast.makeText(getApplicationContext(),"Monthly Expenditure updated",Toast.LENGTH_SHORT).show();
                    MonEx.setText(newMonthExpend);

                }
            }
        };
        asyncHttpPost.execute();
    }

    private void UpdateAddress(String email,String newResidenceAddress,TextView rAddress){

        ContentValues parameters = new ContentValues();
        parameters.put("Email", email );
        parameters.put("DetailID", "3");
        parameters.put("Detail",newResidenceAddress);


        //TODO
        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/UpdateInfo.php",parameters){
            @Override
            protected void onPostExecute(String output) {

                Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();

                if((output.equals("Residence Address updated"))) {
                    //Go to login page after successful registration.
                    Toast.makeText(getApplicationContext(),"Residence Address updated",Toast.LENGTH_SHORT).show();
                    rAddress.setText(newResidenceAddress);

                }
            }
        };
        asyncHttpPost.execute();
    }

}