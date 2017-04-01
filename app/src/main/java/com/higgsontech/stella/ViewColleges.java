package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloud on 31/3/17.
 */

public class ViewColleges  extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener,LoaderManager.LoaderCallbacks<List<Center>> {

    public static final String LOG_TAG = ViewColleges.class.getSimpleName();


    private GoogleApiClient apiClient;
    private LocationRequest locationRequest;
    private String lat;
    private String alt;
    private String ltd;
    private String output;
    private CenterAdapter centerAdapter;
    View progressBar;



    private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String API_KEY="AIzaSyC16Z0laYhW8Py27WMT6R9GpFT_HbziBFE";
    private static final String GET_CENTERS_URL="http://higgsontech.com/hack/fetchFixedCenters.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_colleges);

        final LoaderManager loaderManager=getLoaderManager();


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        apiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

        final ListView centerListView=(ListView)findViewById(R.id.centerListView);
        centerAdapter=new CenterAdapter(ViewColleges.this,0,new ArrayList<Center>());
        centerListView.setAdapter(centerAdapter);


        Button loadCenters=(Button)findViewById(R.id.loadFixedCenters);
        loadCenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loaderManager.initLoader(5,null,ViewColleges.this);


            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();
    }

    @Override
    protected void onStop() {
        apiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        lat=String.valueOf(location.getLatitude());
        alt=String.valueOf(location.getAltitude());
        ltd=String.valueOf(location.getLongitude());
        output="lat: "+lat+",  lng: "+ltd;
        Log.e("location",output);

    }

    @Override
    public Loader<List<Center>> onCreateLoader(int id, Bundle args) {

            Uri uri=Uri.parse(PLACES_TEXT_SEARCH_URL);
            Uri.Builder builder=uri.buildUpon();
            builder.appendQueryParameter("location",lat+","+ltd);
            // builder.appendQueryParameter("radius","5000");
            builder.appendQueryParameter("keyword","schools");
            builder.appendQueryParameter("rankby","distance");
            builder.appendQueryParameter("key",API_KEY);


            return new CenterLoader(this, builder.toString(), "5");

    }

    @Override
    public void onLoadFinished(Loader<List<Center>> loader, List<Center> data) {

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