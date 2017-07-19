package com.example.user.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.example.user.login.Helper.url_link;


public class RegisterActivity extends Activity {

    Button buttonRegister;
    TextView login;
    private EditText input_firstname, input_lastname, input_phone, input_email, input_username,
            input_password, input_greetings, input_title, input_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        input_firstname = (EditText) findViewById(R.id.signup_input_firstname);
        input_lastname = (EditText) findViewById(R.id.signup_input_lastname);
        input_phone = (EditText) findViewById(R.id.signup_input_phone);
        input_email = (EditText) findViewById(R.id.signup_input_email);
        input_username = (EditText) findViewById(R.id.signup_input_username);
        input_password = (EditText) findViewById(R.id.signup_input_password);
        input_greetings = (EditText) findViewById(R.id.signup_input_greetings);
        input_title = (EditText) findViewById(R.id.signup_input_title);
        input_pin = (EditText) findViewById(R.id.signup_input_pin);

        login = (TextView) findViewById(R.id.link_login);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstname = input_firstname.getText().toString();
                String lastname = input_lastname.getText().toString();
                String phone = input_phone.getText().toString();
                String email = input_email.getText().toString();
                String username = input_username.getText().toString();
                String password = input_password.getText().toString();
                String greetings = input_greetings.getText().toString();
                String title = input_title.getText().toString();
                String pin = input_pin.getText().toString();
                String type = "register";
                if (TextUtils.isEmpty(firstname)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Firstname is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(lastname)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Lastname is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Phone is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Email is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(username)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Username is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Password is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(greetings)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Greetings is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(title)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Title is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else if (TextUtils.isEmpty(pin)) {
                    Toast msg = Toast.makeText(RegisterActivity.this, "Pin is Empty.", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                } else {
                    new Masuk().execute(type,firstname,lastname,phone,email,username, password,greetings,title,pin);
                }
            }
        });
    }

    private class Masuk extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        AlertDialog alertDialog;

        @Override
        public String doInBackground(String... params) {
            String type = params[0];
            String login_url;
            url_link link = new url_link();
            login_url = link.getUrl_link(type);
            if (type.equals("register")) {
                try {
                    String firstname = params[1];
                    String lastname = params[2];
                    String phone = params[3];
                    String email = params[4];
                    String username = params[5];
                    String password = params[6];
                    String greetings = params[7];
                    String title = params[8];
                    String pin = params[9];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputstream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
                    String post_data = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8")
                            + "&" + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8")
                            + "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                            + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                            + "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                            + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                            + "&" + URLEncoder.encode("greetings", "UTF-8") + "=" + URLEncoder.encode(greetings, "UTF-8")
                            + "&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8")
                            + "&" + URLEncoder.encode("pin", "UTF-8") + "=" + URLEncoder.encode(pin, "UTF-8")
                            ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle("Register Status");
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Wait a Moment...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            JSONObject jsonObj = null;
            String reason = "";


            int status = 100;
            try

            {
                jsonObj = XML.toJSONObject(result);
                JSONObject jObj = new JSONObject(jsonObj.toString());
                JSONObject MedMasterUser = jObj.getJSONObject("MedMasterUser");
                status = MedMasterUser.getInt("status");
                reason = MedMasterUser.getString("reason");

            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }
            if (status == 0) {
                alertDialog.setMessage("login sukses");
                alertDialog.show();
                Intent Home = new Intent(RegisterActivity.this, LoginActivity.class);
                alertDialog.dismiss();
                finish();
                startActivity(Home);

            } else {
                alertDialog.setMessage(reason);
                alertDialog.show();

            }
        }


    }


}
