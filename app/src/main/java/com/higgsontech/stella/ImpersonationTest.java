package com.higgsontech.stella;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by GUR23835 on 4/2/2017.
 */

public class ImpersonationTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard2);

        ImageView right = (ImageView) findViewById(R.id.imageView2);
        ImageView wrong = (ImageView) findViewById(R.id.imageView3);

    }
}
