package com.higgsontech.stella.Roles;

/**
 * Created by GUR23835 on 4/1/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewColleges;

public class ZoneHead extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView zoStudentAlloc;
    private ImageView zoCenter;
    private ImageView zoAttendance;
    private ImageView zoProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zonalofficer);

        zoStudentAlloc = (ImageView) findViewById(R.id.zoStudentAllocation);
        zoCenter = (ImageView) findViewById(R.id.zoChooseCenter);
        zoAttendance = (ImageView) findViewById(R.id.zoAttendance);
        zoProfile = (ImageView) findViewById(R.id.zoViewProfile);

        zoStudentAlloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        zoCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
                startActivity(viewCollegeIntent);
            }
        });

        zoAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        zoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Allocation of student on center
//                Intent viewCollegeIntent = new Intent(ZoneHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });
    }


}