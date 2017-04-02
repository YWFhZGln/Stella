package com.higgsontech.stella.Roles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.higgsontech.stella.R;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class OSDS  extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView osdsStudentAllocation;
    private ImageView osdsImpersonation;
    private ImageView osdsPaymentStatus;
    private ImageView osdsPermission;
    private ImageView osdsViewProfile;
    private ImageView osdsAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.osds);


        osdsStudentAllocation = (ImageView) findViewById(R.id.osdsStudentAllocation);
        osdsStudentAllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        osdsAttendance = (ImageView) findViewById(R.id.osdsAttendance);
        osdsAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        osdsImpersonation = (ImageView) findViewById(R.id.osdsImpersonation);
        osdsImpersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        osdsPaymentStatus = (ImageView) findViewById(R.id.osdsPaymentStatus);
        osdsPaymentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        osdsPermission = (ImageView) findViewById(R.id.osdsPermission);
        osdsPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        osdsViewProfile = (ImageView) findViewById(R.id.osdsViewProfile);
        osdsViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });
    }
}
