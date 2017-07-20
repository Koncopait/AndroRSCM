package com.example.user.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.login.Helper.UserSessionManager;

import java.util.HashMap;


public class HomeActivity extends Activity {
    TextView nama, username;
    String get_nama;
    String get_username;
    UserSessionManager session;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        nama = (TextView) findViewById(R.id.hore);
        session = new UserSessionManager(getApplicationContext());




        if(session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();



        nama.setText("Nama : "+user.get(UserSessionManager.KEY_USERNAME)+" "+user.get(UserSessionManager.KEY_PASSWORD)+" "+user.get(UserSessionManager.KEY_TOKEN));




        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                session.logoutUser();
            }

        });
    }

}