package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewFixedCentersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Center>> {

    private static final String GET_CENTERS_URL="http://higgsontech.com/hack/fetchFixedCenters.php";

    View progressBar;
    private CenterAdapter centerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fixed_centers);

        final LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(9,null,this);


        progressBar = findViewById(R.id.progressBar);

        final ListView centerListView=(ListView)findViewById(R.id.centerListView);
        centerAdapter=new CenterAdapter(this,0,new ArrayList<Center>());
        centerListView.setAdapter(centerAdapter);



    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new FixedCenterLoader(this,GET_CENTERS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Center>> loader, List<Center> data) {



    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
