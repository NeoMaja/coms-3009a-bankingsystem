package com.example.coms_3009a_banking_system.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Client_Verification_Status;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.ClientAccount.client_account;
import com.example.coms_3009a_banking_system.usertype;

public class Login2 extends AppCompatActivity {

    EditText Get_email, Get_Password;
    Button Login, Register;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        System.err.println("Login Pressed");
        //finding views by ids
        Login = (Button)findViewById(R.id.login_id);
        Register = (Button)findViewById(R.id.new_acc_id);
        Get_email = (EditText)findViewById(R.id.username_l);
        Get_Password = (EditText)findViewById(R.id.password_l);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //get email address and password,
                email = Get_email.getText().toString();
                password = Get_Password.getText().toString();

                //send to php files
                ContentValues parameters = new ContentValues();
                parameters.put("email", email);
                parameters.put("password", password);

                AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/userLogin.php",parameters){
                    @Override
                    protected void onPostExecute(String output) {
                        //output UserTypeID
                        //if 300 go to AdminLoginKey
                        if(output.equals("300")){
                            Intent intent = new Intent(Login2.this, AdminLoginKey.class); // Intent should take you to Admin Verification Page.
                                                                                                        // Should Be able to see Admin profile
                            intent.putExtra("email", email);
                            intent.putExtra("password",password);
                            startActivity(intent);
                        }

                        //if 250verified go to Client_account
                        if(output.equals("250verified")){

                            Intent intent = new Intent(Login2.this, client_account.class); // this takes us to the Client Account Page
                                                                                                            // email and password to be used to get info for Profile page
                            intent.putExtra("email", email);
                            intent.putExtra("password",password);
                           // intent.putExtra("status",status);
                            startActivity(intent);
                        }

                        //if 250unverified go to Client_Verification_Status
                        if(output.equals("250un-verified")){
                            String status ="un-verified";
                            Intent intent = new Intent(Login2.this, Client_Verification_Status.class);
                            intent.putExtra("email", email);
                            intent.putExtra("status",status);

                            startActivity(intent);
                        }
                    }
                };
                asyncHttpPost.execute();

            }
        });

        //to register go to reg userType page
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent intent = new Intent(Login2.this, usertype.class);
                startActivity(intent);
            }
        });


    }
}