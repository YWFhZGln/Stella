package com.higgsontech.stella;
//
//import android.os.Build;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import static com.higgsontech.stella.R.id.email;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//
//
//    EditText et_fname,et_lname,et_aadhar,et_office_pin,et_home_pin,et_signup_email,et_signup_password,et_mobile, et_email,et_password;
//    String u_fname,u_lname,u_gender,u_aadhar,u_designation,u_office_pin,u_home_pin,u_signup_email,u_signup_password,u_mobile,u_email,u_password;
//
//    public void showSignUp(View view) {
//        LinearLayout s = (LinearLayout) findViewById(R.id.signup_layout);
//        LinearLayout l = (LinearLayout) findViewById(R.id.login_layout);
//
//        l.setVisibility(View.GONE);
//        s.setVisibility(View.VISIBLE);
//    }
//
//    public void showLogIn(View view) {
//        LinearLayout s = (LinearLayout) findViewById(R.id.signup_layout);
//        LinearLayout l = (LinearLayout) findViewById(R.id.login_layout);
//
//        s.setVisibility(View.GONE);
//        l.setVisibility(View.VISIBLE);
//
//    }
//
//    public void register(View view)
//    {
//        et_fname               =   (EditText) findViewById(R.id.fname);
//        et_lname               =   (EditText) findViewById(R.id.lname);
//        et_aadhar              =   (EditText) findViewById(R.id.aadhar);
//        et_office_pin          =   (EditText) findViewById(R.id.office_pin);
//        et_home_pin            =   (EditText) findViewById(R.id.home_pin);
//        et_signup_email        =   (EditText) findViewById(R.id.signup_email);
//        et_signup_password     =   (EditText) findViewById(R.id.signup_password);
//        et_mobile              =    (EditText) findViewById(R.id.mobile);
//
//        u_fname                   =   et_fname.getText().toString();
//        u_lname                   =   et_lname.getText().toString();
//        u_gender                  =   "Male";
//        u_aadhar                  =   et_aadhar.getText().toString();
//        u_designation             =   "Center Head";
//        u_office_pin              =   et_office_pin.getText().toString();
//        u_home_pin                =   et_home_pin.getText().toString();
//        u_signup_email            =   et_signup_email.getText().toString();
//        u_signup_password         =   et_signup_password.getText().toString();
//        u_mobile                  =   et_mobile.getText().toString();
//
//        String method   =   "register";
//
//        BackgroundTask backgroundTask=new BackgroundTask(this);
//        backgroundTask.execute(method,u_fname,u_lname,u_gender,u_aadhar,u_designation,u_office_pin,u_home_pin,u_signup_email,u_signup_password,u_mobile);
//
//    }
//
//    public void login(View view)
//    {
//        et_email        =   (EditText) findViewById(email);
//        et_password     =   (EditText) findViewById(R.id.password);
//        u_email         =   et_email.getText().toString();
//        u_password      =   et_password.getText().toString();
//        String method   =   "login";
//
//        BackgroundTask backgroundTask=new BackgroundTask(this);
//        backgroundTask.execute(method,u_email,u_password);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void gotoLoginActivity(View view){
        Intent i= new Intent(this,Dashboard.class);
        startActivity(i);
    }

}