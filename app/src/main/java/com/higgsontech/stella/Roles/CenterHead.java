package com.higgsontech.stella.Roles;

/**
 * Created by GUR23835 on 4/1/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.higgsontech.stella.R;
import com.higgsontech.stella.ViewColleges;

public class CenterHead extends AppCompatActivity {

    //Textview to show currently logged in user
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecollege);

        //Initializing textview
        imageView = (ImageView) findViewById(R.id.choose_college);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCollegeIntent = new Intent(CenterHead.this, ViewColleges.class);
                startActivity(viewCollegeIntent);
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