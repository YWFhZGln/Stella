package com.higgsontech.stella;


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

    public void gotoLoginActivity(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}