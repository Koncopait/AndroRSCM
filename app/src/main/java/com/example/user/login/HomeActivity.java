package com.example.user.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {
    TextView nama, username;
    String get_nama;
    String get_username;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        nama = (TextView) findViewById(R.id.hore);
        username = (TextView) findViewById(R.id.username);
        Bundle b = getIntent().getExtras();

        get_nama = b.getString("token");
        get_username = b.getString("username");
        nama.setText("Nama : "+get_nama);
        username.setText("First Name : "+get_username);


        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }

}