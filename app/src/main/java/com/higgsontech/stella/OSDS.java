package com.higgsontech.stella;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by GUR23835 on 4/1/2017.
 */

public class OSDS  extends AppCompatActivity {

    //Textview to show currently logged in user
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_head);
    }
}
