package com.higgsontech.stella.Roles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.AuthActivity;
import com.higgsontech.stella.CityActivity;
import com.higgsontech.stella.PaymentStatus;
import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewFixedCentersActivity;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class CentralOfficer extends AppCompatActivity {

    private ImageView coChooseCollege;
    private ImageView coChooseCenter;
    private ImageView coAttendance;
    private ImageView coViewProfile;
    private ImageView coImpersonation;
    private ImageView coPaymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centralofficer);

        final com.higgsontech.stella.Roles.CentralOfficer ctx = this;
        coChooseCollege = (ImageView) findViewById(R.id.coChooseCollege);
        coChooseCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, CityActivity.class);
                startActivity(viewCollegeIntent);
            }
        });

        coChooseCenter = (ImageView) findViewById(R.id.coChooseCenter);
        coChooseCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, ViewFixedCentersActivity.class);
                startActivity(i);
            }
        });

        coAttendance = (ImageView) findViewById(R.id.coAttendance);
        coAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        coViewProfile = (ImageView) findViewById(R.id.coViewProfile);
        coViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });
        coPaymentStatus = (ImageView) findViewById(R.id.coPaymentStatus);
        coPaymentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, PaymentStatus.class);
                startActivity(i);
            }
        });
        coImpersonation = (ImageView) findViewById(R.id.coImpersonation);
        coImpersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, AuthActivity.class);
                startActivity(i);
            }
        });
    }

}

