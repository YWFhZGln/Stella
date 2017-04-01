package com.higgsontech.stella.Roles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.R;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class CentralOfficer extends AppCompatActivity {

    private ImageView coChooseCollege;
    private ImageView coChooseCenter;
    private ImageView coAttendance;
    private ImageView coViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centralofficer);

        coChooseCollege = (ImageView) findViewById(R.id.coChooseCollege);
        coChooseCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
            }
        });

        coChooseCenter = (ImageView) findViewById(R.id.coChooseCenter);
        coChooseCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
//                startActivity(viewCollegeIntent);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.choose_college) {
            //calling logout method when the logout button is clicked
            //chooseCollege();

            Log.v("Test", "test");
        }
        return super.onOptionsItemSelected(item);
    }

    private void chooseCollege() {
    }
}