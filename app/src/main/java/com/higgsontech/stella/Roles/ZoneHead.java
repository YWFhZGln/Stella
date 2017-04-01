package com.higgsontech.stella.Roles;

/**
 * Created by GUR23835 on 4/1/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.higgsontech.stella.R;

public class ZoneHead extends AppCompatActivity {

    //Textview to show currently logged in user
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_head);

    }


}