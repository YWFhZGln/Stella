package com.higgsontech.stella.Roles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.AuthActivity;
import com.higgsontech.stella.PaymentStatus;
import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewFixedCentersActivity;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class Invigilator  extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView iPaymentStatus;
    private ImageView iImpersonation;
    private ImageView iAttendance;
    private ImageView iViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invigilator);
final Invigilator ctx = this;
        iPaymentStatus = (ImageView) findViewById(R.id.iPaymentStatus);
        iPaymentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, PaymentStatus.class);
                startActivity(viewCollegeIntent);
            }
        });

        iImpersonation = (ImageView) findViewById(R.id.iImpersonation);
        iImpersonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(ctx, AuthActivity.class);
                startActivity(viewCollegeIntent);
            }
        });

        iAttendance = (ImageView) findViewById(R.id.iAttendance);
        iAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        iViewProfile = (ImageView) findViewById(R.id.iViewProfile);
        iViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
