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
    private FixedCenterAdapter centerAdapter;
    //private ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fixed_centers);

        final LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(9,null,this);


        progressBar = findViewById(R.id.progressBar2);

        ListView centerListView=(ListView)findViewById(R.id.centerLV);

        centerAdapter=new FixedCenterAdapter(ViewFixedCentersActivity.this,0,new ArrayList<Center>());
        centerListView.setAdapter(centerAdapter);



    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new FixedCenterLoader(this,GET_CENTERS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Center>> loader, List<Center> data) {
        View progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        centerAdapter.clear();
        centerAdapter.addAll(data);

    }




    @Override
    public void onLoaderReset(Loader loader) {

    }
}
