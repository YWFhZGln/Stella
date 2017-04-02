package com.higgsontech.stella.Roles;

/**
 * Created by GUR23835 on 4/1/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.AuthActivity;
import com.higgsontech.stella.CityActivity;
import com.higgsontech.stella.PaymentStatus;
import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewCenters;
import com.higgsontech.stella.ViewFixedCentersActivity;
import com.higgsontech.stella.ViewProfile;

public class CenterHead extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView echExamCenter;
    private ImageView echChooseCollege;
    private ImageView echSelectExamCenter;
    private ImageView echPayments;
    private ImageView echCalender;
    private ImageView echViewProfile;
    private ImageView eshImpersonation;
    private ImageView eshPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_head);

        final CenterHead ctx = this;
        //Initializing textview
        echExamCenter = (ImageView) findViewById(R.id.echExamCenter);
        echExamCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, CityActivity.class);
                startActivity(i);
            }
        });
        echChooseCollege = (ImageView) findViewById(R.id.echChooseCollege);
        echChooseCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, ViewFixedCentersActivity.class);
                startActivity(viewCollegeIntent);
            }
        });
        echSelectExamCenter = (ImageView) findViewById(R.id.echSelectExamCenter);
        echSelectExamCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, ViewCenters.class);
                startActivity(viewCollegeIntent);
            }
        });
        echViewProfile = (ImageView) findViewById(R.id.echViewProfile);
        echViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, ViewProfile.class);
                startActivity(viewCollegeIntent);
            }
        });
        echCalender = (ImageView) findViewById(R.id.echCalender);
        echCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });
        echPayments = (ImageView) findViewById(R.id.echPayments);
        echPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, PaymentStatus.class);
                startActivity(viewCollegeIntent);
            }
        });
        eshImpersonation = (ImageView) findViewById(R.id.eshImpersonation);
        eshImpersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, AuthActivity.class);
                startActivity(viewCollegeIntent);
            }
        });
        eshPermission = (ImageView) findViewById(R.id.eshPermission);
        eshPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });
    }


}