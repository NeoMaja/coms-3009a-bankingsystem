package com.example.coms_3009a_banking_system;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class  register extends AppCompatActivity {

    private TextInputEditText IDnumber,Phonenumber,Username,FirstName, LastName,Email,Password,cPassword;
    private RadioGroup Gender;
    private ProgressBar progressbar;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username =(TextInputEditText) findViewById(R.id.username);
        FirstName =(TextInputEditText) findViewById(R.id.firstname);
        LastName =(TextInputEditText) findViewById(R.id.lastname);
        IDnumber =(TextInputEditText) findViewById(R.id.idnumber);
        Phonenumber =(TextInputEditText) findViewById(R.id.phonenumber);
        Email =(TextInputEditText) findViewById(R.id.email);
        Password =(TextInputEditText) findViewById(R.id.password);
        cPassword =(TextInputEditText) findViewById(R.id.cpassword);
        Gender = (RadioGroup) findViewById(R.id.radiosex);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        btnDisplay = (Button) findViewById(R.id.create_acc);
        progressbar.setVisibility(View.GONE);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoRegister();

            }
        });

    }

    private static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    private void validateDataAndDoRegister() {
        String username = Username.getText().toString().trim();
        String firstname = LastName.getText().toString().trim();
        String lastname = FirstName.getText().toString().trim();
        String idnumber = IDnumber.getText().toString().trim();
        String phonenumber = Phonenumber.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String c_password = cPassword.getText().toString().trim();
        String gender = null;
        String assistance = null;

        if(Gender.getCheckedRadioButtonId()!=-1){
            int id= Gender.getCheckedRadioButtonId();
            View radioButton = Gender.findViewById(id);
            int radioId = Gender.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) Gender.getChildAt(radioId);
            gender = btn.getText().toString().trim();

        }

        if (username.isEmpty()) {
            Username.setError("Enter username");
            Username.requestFocus();
        }
        else if(username.length()<5){
            Username.setError("Username too short");
            Username.requestFocus();
        }
        else if (firstname.isEmpty()){
            FirstName.setError("Enter first name");
            FirstName.requestFocus();
        }
        else if (lastname.isEmpty()){
            LastName.setError("Enter last name");
            LastName.requestFocus();
        }
        else if (idnumber.isEmpty()){
            IDnumber.setError("Enter ID number");
            IDnumber.requestFocus();
        }
        else if(idnumber.length()<13){
            IDnumber.setError("ID number invalid");
            IDnumber.requestFocus();
        }
        else if (phonenumber.isEmpty()){
            Phonenumber.setError("Enter cellphone number");
            Phonenumber.requestFocus();
        }
        else if(phonenumber.length()<10){
            Phonenumber.setError("Cellphone number invalid");
            Phonenumber.requestFocus();
        }
        else if (email.isEmpty()){
            Email.setError("Enter email address");
            Email.requestFocus();
        }
        else if(email.length()<10){
            Email.setError("Email address invalid");
            Email.requestFocus();
        }
        else if (password.isEmpty()){
            Password.setError("Enter password");
            Password.requestFocus();
        }else if(password.length()<8){
            Password.setError("Password must be 8 characters or more");
            Password.requestFocus();
        }
        else if (c_password.isEmpty()) {
            cPassword.setError("Enter password");
            cPassword.requestFocus();
        }
        else if (!password.equals(c_password)){
            Password.setError("Passwords do not match");
            Password.requestFocus();
            cPassword.setError("Passwords do not match");
            cPassword.requestFocus();
            Password.setText("");
            cPassword.setText("");
        }
        else{
            doRegister(username,email,password,gender,assistance);
            progressbar.setVisibility(View.VISIBLE);
            if(isNetworkStatusAvialable (getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);
            }
        }
    }


    private void doRegister(String username, String email, String password, String gender, String assistance) {
        OkHttpClient client = new OkHttpClient();

///////////////////////////////meng's DATABASE///////////////////////////////////////////////////////////////////////////
        HttpUrl.Builder urlBuilder = HttpUrl.parse("").newBuilder();
        urlBuilder.addQueryParameter("username", username);
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("password", password);
        urlBuilder.addQueryParameter("gender", gender);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                register.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jO = new JSONObject(responseData);
                            String success = jO.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(register.this, "Register Success", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                                Intent loginIntent = new Intent(register.this,MainActivity.class);
                                startActivity(loginIntent);
                                register.this.finish();
                            }
                            if(success.equals("-1")) {
                                Toast.makeText(register.this, "Email Registered", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                            }
                            if(success.equals("2")) {
                                Toast.makeText(register.this, "Username registered", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(register.this, "Register Error", Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
            }
        });
    }
}
