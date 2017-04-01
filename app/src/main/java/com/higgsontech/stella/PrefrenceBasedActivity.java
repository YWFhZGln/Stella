package com.higgsontech.stella;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class PrefrenceBasedActivity extends Activity{
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1=(Button)findViewById(R.id.loginButton);

    }

    public  void logout(View view){
//       SharedPreferences.Editor editor = Constants.sharedpreferences.edit();
//        editor.clear();
//        editor.commit();
    }

    public void login(View view){
//        BackgroundTask bt = new BackgroundTask(this);
//        bt.execute("login",Constants.sharedpreferences.getString(Constants.Email, Constants.Email).toString(), Constants.sharedpreferences.getString(Constants.Passwd,Constants.Passwd) );
    }
}
