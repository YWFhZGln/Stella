package com.higgsontech.stella;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ishu on 30/3/17.
 */

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void FixExamCenter(View view)
    {
        Intent i= new Intent(this,FixExamCenter.class);
        startActivity(i);
    }
    public void ViewColleges(View view)
    {
        Intent i= new Intent(this,ViewColleges.class);
        startActivity(i);
    }
    public void ImpersonationCheck(View view)
    {
        Intent i= new Intent(this,ImpersonationCheck.class);
        startActivity(i);
    }
    public void PaymentStatus(View view)
    {
        Intent i= new Intent(this,PaymentStatus.class);
        startActivity(i);
    }
    public void Attendance(View view)
    {
        Intent i= new Intent(this,Attendance.class);
        startActivity(i);
    }
    public void ViewProfile(View view)
    {
        Intent i= new Intent(this,ViewProfile.class);
        startActivity(i);
    }
}