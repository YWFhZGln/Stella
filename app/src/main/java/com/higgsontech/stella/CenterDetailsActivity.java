package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_details);

        Intent i=getIntent();
        s=i.getStringArrayExtra("itemData");

//        Bundle bundle=getIntent().getExtras();
//        ArrayList<String> centerData=(ArrayList<String>)bundle.getStringArrayList("itemData");
      Log.d("id",s[0]);

        TextView centerName=(TextView)findViewById(R.id.centerName);
        TextView sCapacity=(TextView)findViewById(R.id.sCapacity);
        TextView fCapacity=(TextView)findViewById(R.id.fCapacity);
        Button fixCenterButton=(Button)findViewById(R.id.fixCenter);
        Button cancelButton=(Button)findViewById(R.id.goBack);
        centerName.setText(s[0]);
        String studentCapacity=sCapacity.getText().toString();
        String facultyCapacity=fCapacity.getText().toString();


        fixCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new fixCenter(this,s[2]);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }


    @Override
    public void onLoaderReset(Loader loader) {

    }

    public class fixCenter extends AsyncTaskLoader<String> {

        private String mPlaceId;

        public fixCenter(Context context, String placeId) {
            super(context);
            mPlaceId = placeId;
        }

        @Override
        public String loadInBackground() {

            String res=makeHttpRequest(mPlaceId);
            return res;
        }

        private String makeHttpRequest(String pId) {
            URL url = null;
            try {
                url = new URL("http://");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

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

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("centerPlaceId",pId);
                String query = builder.build().getEncodedQuery();


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
            return response;
        }
    }
}
