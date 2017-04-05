package com.higgsontech.stella;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.higgsontech.stella.Utils.BackgroundTask;
import com.higgsontech.stella.Utils.Constants;

/**
 * Created by ishu on 2/4/17.
 */

public class Permissions extends AppCompatActivity {

    TextView status;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions);

        status = (TextView) findViewById(R.id.permissionStatus);
        b = (Button) findViewById(R.id.getPermisssionButton);
        final Context ctx = this;
        if(Constants.PERMISSION_PENDING.equals(null)) {


            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String method = "permission";
                    String subMethod = "getPermission";
                    String permissionId = null;
                    String userId = Constants.fetchSharedPreferenceValues(Constants.id);
                    BackgroundTask backgroundTask = new BackgroundTask(ctx);
                    backgroundTask.execute(method, subMethod, permissionId, userId);
                }
            });
        }
        else if(!Constants.PERMISSION_PENDING.equals(null))
        {
            status.setText("Permission Pending");
            b.setVisibility(View.GONE);
        }

    }
}
