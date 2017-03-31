package com.higgsontech.stella;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        String[] cities={"Karnal","Panipat","Kurukshetra","Delhi","Ambala"};

        ListView cityList=(ListView)findViewById(R.id.cityList);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
        cityList.setAdapter(arrayAdapter);


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=parent.getItemAtPosition(position).toString();
                Log.e("item",item);

                ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null&&networkInfo.isConnected()) {

                    Intent intent=new Intent(CityActivity.this,CenterListActivity.class);
                    intent.putExtra("city",item);
                    startActivity(intent);
                } else {
                    Snackbar.make(view,"No internet connection", Snackbar.LENGTH_SHORT).show();
                }
            }
        });




    }
}
