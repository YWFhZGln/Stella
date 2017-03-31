package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;

public class CenterListActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,LoaderManager.LoaderCallbacks<List<Center>> {

    public static final String LOG_TAG = CenterListActivity.class.getSimpleName();

    private String cityName;


    private GoogleApiClient apiClient;
    private CenterAdapter centerAdapter;
    private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
    private static final String API_KEY="AIzaSyC16Z0laYhW8Py27WMT6R9GpFT_HbziBFE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_list);


        final Intent intent=getIntent();
        cityName=intent.getStringExtra("city");

        apiClient=new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build();


        final ListView centerListView=(ListView)findViewById(R.id.centerListView);
        centerAdapter=new CenterAdapter(CenterListActivity.this,0,new ArrayList<Center>());
        centerListView.setAdapter(centerAdapter);


        centerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               long itemId= centerListView.getAdapter().getItemId(position);
                Center data=(Center)parent.getItemAtPosition(position);
//                ArrayList<String> centerData=new ArrayList<String>();
//                centerData.add(data.getName());
//                centerData.add(data.getAddress());
//                centerData.add(data.getPlaceId());
////
                String[] centerData=new String[]{data.getName(),data.getAddress(),data.getPlaceId()};
             Log.e(LOG_TAG,centerData[1]);
                Intent intent1=new Intent(CenterListActivity.this,CenterDetailsActivity.class);
                intent1.putExtra("itemData",centerData);

                startActivity(intent1);
            }
        });

        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(0,null,this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        apiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public Loader<List<Center>> onCreateLoader(int id, Bundle args) {

        Uri uri=Uri.parse(PLACES_TEXT_SEARCH_URL);
        Uri.Builder builder=uri.buildUpon();
        builder.appendQueryParameter("query","govt"+"school"+"in"+cityName);
        builder.appendQueryParameter("key",API_KEY);

        return new CenterLoader(this,builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Center>> loader, List<Center> data) {

        Log.e(LOG_TAG,"onloadfinished");
        View progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        centerAdapter.clear();
        if (data != null && !data.isEmpty()) {
            centerAdapter.addAll(data);
        }



    }

    @Override
    public void onLoaderReset(Loader<List<Center>> loader) {
        centerAdapter.clear();

    }
}
