package com.example.coms_3009a_banking_system;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Client_Verification_Status extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_verification_status);
        TextView status = (TextView) findViewById(R.id.verification_status);
        status.setText("Hope");



    }
}
