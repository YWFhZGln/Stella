package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloud on 31/3/17.
 */

public class ViewCenters extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Center>> {

    public static final String LOG_TAG = ViewCenters.class.getSimpleName();
    private int PLACE_PICKER_REQUEST = 1;


    private GoogleApiClient apiClient;
    private LocationRequest locationRequest;
    private String lat;
    private String ltd;
    private String output;
    private CenterAdapter centerAdapter;
    View progressBar;
    private TextView location;
    private Button getloc;
    private Button getCenters;


    private static final String GET_CENTERS_URL="http://higgsontech.com/hack/fetchFixedCenters.php";


    private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String API_KEY="AIzaSyC16Z0laYhW8Py27WMT6R9GpFT_HbziBFE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_centers);

        final LoaderManager loaderManager=getLoaderManager();

        location=(TextView)findViewById(R.id.location);


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        getCenters.setVisibility(View.GONE);



        final ListView centerListView=(ListView)findViewById(R.id.centerListView);
        centerAdapter=new CenterAdapter(ViewCenters.this,0,new ArrayList<Center>());
       // centerListView.setAdapter(centerAdapter);
        loaderManager.initLoader(5,null,ViewCenters.this);


        getloc = (Button)findViewById(R.id.getLocation);
        getloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getloc.setVisibility(View.GONE);



                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(ViewCenters.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });







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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                progressBar.setVisibility(View.GONE);
                LatLngBounds place = PlacePicker.getLatLngBounds(data);
                LatLng latLng=place.getCenter();
                lat = String.format("%s", latLng.latitude);
                ltd = String.format("%s", latLng.longitude);
                String pl=String.format("Selected Location: %s",PlacePicker.getPlace(this,data).getAddress());
                location.setText(pl);
                getCenters.setVisibility(View.VISIBLE);




            }
        }
    }


}