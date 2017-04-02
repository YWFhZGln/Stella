package com.higgsontech.stella;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class PermissionDenied extends AppCompatActivity {

    //Textview to show currently logged in user
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions);
        button = (Button) findViewById(R.id.getPermisssionButton);

        //Initializing textview
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PermissionDenied.this);
                alertDialogBuilder.setMessage("Do you want to send reminder to the authorizing officer?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PermissionDenied.this);
                        builder.setMessage("Message Sent!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
                alertDialogBuilder.show();
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

}