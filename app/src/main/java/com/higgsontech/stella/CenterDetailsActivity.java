package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CenterDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    String[] s;
    String placeId;
    String name;
    String add;

    String roomNum;
    String studentCapacity;
    String facultyCapacity;
    String centerType;
   private EditText sCap;
    private EditText roomNums;
   private EditText fCapacity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_details);

        Intent i=getIntent();
        s=i.getStringArrayExtra("itemData");

//        Bundle bundle=getIntent().getExtras();
//        ArrayList<String> centerData=(ArrayList<String>)bundle.getStringArrayList("itemData");
      Log.d("id",s[0]);

        EditText centerName=(EditText)findViewById(R.id.centerName);
        Button fixCenterButton=(Button)findViewById(R.id.fixCenter);
        Button cancelButton=(Button)findViewById(R.id.goBack);
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.selectType);
        centerName.setText(s[0]);
        Log.e("ghfg",roomNum+"room");



        fixCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ghfg","click");
                sCap = (EditText)findViewById(R.id.sCapacity);
                roomNums = (EditText)findViewById(R.id.roomNumbers);
                fCapacity = (EditText)findViewById(R.id.fCapacity);
                placeId = s[2];
                add=s[1];
                name = s[0];
                roomNum = roomNums.getText().toString();
                studentCapacity = sCap.getText().toString();
                facultyCapacity = fCapacity.getText().toString();




                LoaderManager loaderManager=getLoaderManager();
                loaderManager.initLoader(1,null,CenterDetailsActivity.this);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //NavUtils.navigateUpFromSameTask(CenterDetailsActivity.this);
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.e("ghfg","radio");

                if(checkedId == R.id.boys) {
                    centerType="boys only";
                } else if(checkedId == R.id.girls) {
                    centerType="girls only";
                } else {
                    centerType="both";
                }

            }
        });


    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.e("ghfg","oncreate");

        return new FixCenter(this,placeId,name,add,roomNum,studentCapacity,facultyCapacity,centerType);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
//        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(data.toString());
        finish();
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onLoaderReset(Loader loader) {

    }

    public static class FixCenter extends AsyncTaskLoader<String> {

        private String placeId;
        private String name;
        private String address;
        private String noOfRooms;
        private String sCap;
        private String fCap;
        private String centerType;

        public FixCenter(Context context, String pi, String n,String add, String nor, String sc, String fc, String ct) {
            super(context);
            this.placeId=pi;
            this.name=n;
            address=add;
            this.noOfRooms=nor;
            this.sCap=sc;
            this.fCap=fc;
            this.centerType=ct;
        }


        @Override
        protected void onStartLoading() {
            forceLoad();
        }


        @Override
        public String loadInBackground() {
            Log.e("ghfg","loadInBackground");


            String res=makeHttpRequest();
            return res;
        }

        private String makeHttpRequest() {
            URL url = null;
            try {
                url = new URL("http://higgsontech.com/hack/fixcenters.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Log.e("ghfg",noOfRooms+"noofroom");


            String response = "";
            InputStream inputStream = null;
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                Log.e("ghfg","makehttp called");


                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("centerPlaceId",placeId);
                builder.appendQueryParameter("name",name);
                builder.appendQueryParameter("address",address);
                builder.appendQueryParameter("roomNumbers",noOfRooms);
                builder.appendQueryParameter("sCapacity",sCap);
                builder.appendQueryParameter("fCapacity",fCap);
                builder.appendQueryParameter("centerType",centerType);
                String query = builder.build().getEncodedQuery();
                Log.e("query",query);


                OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                bufferedWriter.write(query);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                inputStream=httpURLConnection.getInputStream();
                response=CenterLoader.readFromStream(inputStream);




            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                httpURLConnection.disconnect();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Log.e("dsd",response);
            return response;
        }
    }
}
