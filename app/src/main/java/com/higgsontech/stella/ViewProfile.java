package com.higgsontech.stella;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.higgsontech.stella.Utils.Constants;

import static com.higgsontech.stella.Utils.Constants.fetchSharedPreferenceValues;

/**
 * Created by cloud on 31/3/17.
 */

public class ViewProfile  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);;



        fetchSharedPreferenceValues(Constants.id);


        TextView fname=(TextView) findViewById(R.id.fname);
        TextView lname=(TextView) findViewById(R.id.lname);
        TextView aadhar=(TextView) findViewById(R.id.aadhar);
        TextView office_pin=(TextView) findViewById(R.id.office_pin);
        TextView home_pin=(TextView) findViewById(R.id.home_pin);
        TextView mobile=(TextView) findViewById(R.id.mobile);

        fetchSharedPreferenceValues(Constants.lname);
        fetchSharedPreferenceValues(Constants.designation);
        fetchSharedPreferenceValues(Constants.aadhar);
        fetchSharedPreferenceValues(Constants.office_pin);
        fetchSharedPreferenceValues(Constants.home_pin);
        fetchSharedPreferenceValues(Constants.signup_email);
        fetchSharedPreferenceValues(Constants.signup_password);
        fetchSharedPreferenceValues(Constants.mobile);
        fetchSharedPreferenceValues(Constants.email);
        fetchSharedPreferenceValues(Constants.password);

        String fn=Constants.fetchSharedPreferenceValues(Constants.fname);
        fname.setText(fn);
        String ln=Constants.fetchSharedPreferenceValues(Constants.lname);
        fname.setText(ln);
        String ad=Constants.fetchSharedPreferenceValues(Constants.aadhar);
        fname.setText(ad);
        String op=Constants.fetchSharedPreferenceValues(Constants.office_pin);
        fname.setText(op);
        String hp=Constants.fetchSharedPreferenceValues(Constants.home_pin);
        fname.setText(hp);
        String mobile=Constants.fetchSharedPreferenceValues(Constants.mobile);
        fname.setText(mobile);
    }
}
