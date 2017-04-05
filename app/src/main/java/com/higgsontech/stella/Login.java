package com.higgsontech.stella;

/**
 * Created by cloud on 30/3/17.
 */


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.higgsontech.stella.Roles.CenterHead;
import com.higgsontech.stella.Roles.CentralOfficer;
import com.higgsontech.stella.Roles.Invigilator;
import com.higgsontech.stella.Roles.OSDS;
import com.higgsontech.stella.Roles.ZoneHead;
import com.higgsontech.stella.Utils.BackgroundTask;
import com.higgsontech.stella.Utils.Constants;

import static com.higgsontech.stella.AuthUtils.context;


public class Login extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    EditText et_fname, et_lname, et_aadhar, et_office_pin, et_home_pin, et_signup_email, et_signup_password, et_mobile, et_email, et_password;
    String u_fname, u_lname, u_gender, u_aadhar, u_designation, u_office_pin, u_home_pin, u_signup_email, u_signup_password, u_mobile, u_email, u_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void showSignUp(View view) {
        LinearLayout s = (LinearLayout) findViewById(R.id.signup_layout);
        LinearLayout l = (LinearLayout) findViewById(R.id.login_layout);

        l.setVisibility(View.GONE);
        s.setVisibility(View.VISIBLE);
    }

    public void showLogIn(View view) {
        LinearLayout s = (LinearLayout) findViewById(R.id.signup_layout);
        LinearLayout l = (LinearLayout) findViewById(R.id.login_layout);

        s.setVisibility(View.GONE);
        l.setVisibility(View.VISIBLE);

    }


    public void register(View view) {
        et_fname = (EditText) findViewById(R.id.fname);
        et_lname = (EditText) findViewById(R.id.lname);
        et_aadhar = (EditText) findViewById(R.id.aadhar);
        et_office_pin = (EditText) findViewById(R.id.office_pin);
        et_home_pin = (EditText) findViewById(R.id.home_pin);
        et_signup_email = (EditText) findViewById(R.id.signup_email);
        et_signup_password = (EditText) findViewById(R.id.signup_password);
        et_mobile = (EditText) findViewById(R.id.mobile);

        u_fname = et_fname.getText().toString();
        u_lname = et_lname.getText().toString();
        u_gender = "Male";
        u_aadhar = et_aadhar.getText().toString();
        u_designation = "Center Head";
        u_office_pin = et_office_pin.getText().toString();
        u_home_pin = et_home_pin.getText().toString();
        u_signup_email = et_signup_email.getText().toString();
        u_signup_password = et_signup_password.getText().toString();
        u_mobile = et_mobile.getText().toString();

        String method = "register";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, u_fname, u_lname, u_gender, u_aadhar, u_designation, u_office_pin, u_home_pin, u_signup_email, u_signup_password, u_mobile);

    }


    public void login(View view) {
        et_email = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.password);
        u_email = et_email.getText().toString();
        u_password = et_password.getText().toString();
        String method = "login";

        Constants.setSharedpreferences(getSharedPreferences("MyPref", 0));
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, u_email, u_password);


        try {
            switch (Integer.parseInt(Constants.fetchSharedPreferenceValues(Constants.designation))) {
                case Constants.INT_ROLE_CENTRAL_OFFICER:
                    if (Constants.fetchSharedPreferenceValues(Constants.permission, true)) {
                        Intent coIntent = new Intent(Login.this, CentralOfficer.class);
                        startActivity(coIntent);
                    } else {
                        Intent deniedIntent = new Intent(Login.this, PermissionDenied.class);
                        startActivity(deniedIntent);
                    }
                    break;
                case Constants.INT_ROLE_ZONAL_OFFICER:
                    if (Constants.fetchSharedPreferenceValues(Constants.permission, true)) {
                        Intent zhIntent = new Intent(Login.this, ZoneHead.class);
                        startActivity(zhIntent);
                    } else {
                        Intent deniedIntent = new Intent(Login.this, PermissionDenied.class);
                        startActivity(deniedIntent);
                    }
                    break;
                case Constants.INT_ROLE_OSDS:
                    if (Constants.fetchSharedPreferenceValues(Constants.permission, true)) {
                        Intent osdsIntent = new Intent(Login.this, OSDS.class);
                        startActivity(osdsIntent);
                    } else {
                        Intent deniedIntent = new Intent(Login.this, PermissionDenied.class);
                        startActivity(deniedIntent);
                    }
                    break;
                case Constants.INT_ROLE_EXAM_CENTER_HEAD:
                    if (Constants.fetchSharedPreferenceValues(Constants.permission, true)) {
                        Intent intent = new Intent(Login.this, CenterHead.class);
                        startActivity(intent);
                    } else {
                        Intent deniedIntent = new Intent(Login.this, PermissionDenied.class);
                        startActivity(deniedIntent);
                    }
                    break;
                case Constants.INT_ROLE_INVIGILATOR:
                    if (Constants.fetchSharedPreferenceValues(Constants.permission, true)) {
                        Intent intent = new Intent(Login.this, Invigilator.class);
                        startActivity(intent);
                    } else {
                        Intent deniedIntent = new Intent(Login.this, PermissionDenied.class);
                        startActivity(deniedIntent);
                    }
                default:
                    Log.v("Error State", "Error State");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
//
//    }


//    @Override
//    protected void onStop() {
//        Constants.setSharedpreferences(null);
//        SharedPreferences.Editor editor = getSharedPreferences("MyPref", 0).edit();
//        editor.clear();
//        editor.commit();
//        super.onStop();
//    }
}