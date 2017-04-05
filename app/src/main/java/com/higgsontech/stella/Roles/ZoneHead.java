package com.higgsontech.stella.Roles;

/**
 * Created by GUR23835 on 4/1/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.CityActivity;
import com.higgsontech.stella.PaymentStatus;
import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewFixedCentersActivity;
import com.higgsontech.stella.ViewProfile;

public class ZoneHead extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView zoStudentAllocation;
    private ImageView zoChooseCenter;
    private ImageView zoAttendance;
    private ImageView zoViewProfile;
    private ImageView zoChooseCollege;
    private ImageView zoPaymentStatus;
    private ImageView zoPermission;
    private ImageView zoImpersonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zonalofficer);

        final ZoneHead ctx = this;

        zoAttendance = (ImageView) findViewById(R.id.zoAttendance);
        zoAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        zoChooseCenter = (ImageView) findViewById(R.id.zoChooseCenter);
        zoChooseCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, ViewFixedCentersActivity.class);
                startActivity(viewCollegeIntent);
            }
        });

        zoChooseCollege = (ImageView) findViewById(R.id.zoChooseCollege);
        zoChooseCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
                Intent viewCollegeIntent = new Intent(ctx, CityActivity.class);
                startActivity(viewCollegeIntent);
            }
        });

        zoImpersonation = (ImageView) findViewById(R.id.zoImpersonation);
        zoImpersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ImpersonationTest.class);
//                startActivity(viewCollegeIntent);
            }
        });

        zoPaymentStatus = (ImageView) findViewById(R.id.zoPaymentStatus);
        zoPaymentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
                Intent viewCollegeIntent = new Intent(ctx, PaymentStatus.class);
                startActivity(viewCollegeIntent);
            }
        });

        zoPermission = (ImageView) findViewById(R.id.zoPermission);
        zoPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        zoStudentAllocation = (ImageView) findViewById(R.id.zoStudentAllocation);
        zoStudentAllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        zoViewProfile = (ImageView) findViewById(R.id.zoViewProfile);
        zoViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
                Intent viewCollegeIntent = new Intent(ctx, ViewProfile.class);
                startActivity(viewCollegeIntent);
            }
        });

    }
}