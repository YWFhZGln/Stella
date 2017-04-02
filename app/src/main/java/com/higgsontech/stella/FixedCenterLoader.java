package com.higgsontech.stella;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 2/4/17.
 */

public class FixedCenterLoader extends AsyncTaskLoader<List<Center>> {

    public static final String LOG_TAG = FixedCenterLoader.class.getSimpleName();

    private String url;

    public FixedCenterLoader(Context context, String u) {
        super(context);
        url = u;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Center> loadInBackground() {
        String response = CenterLoader.makeHttpRequest(url);
         List<Center> ids=parse(response);

        return ids;
    }

    private static List<Center> parse(String res) {


        List<Center> fcenterList=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentId = jsonArray.getJSONObject(i);
                String placeId = currentId.getString("placeId");
                String centerName=currentId.getString("name");
                String centerAddress=currentId.getString("address");
                int noofrooms=currentId.getInt("roomnum");
                int scap=currentId.getInt("stdcap");
                int freq=currentId.getInt("facreq");
                String type=currentId.getString("type");
                Log.e(LOG_TAG, centerName);
                Center center=new Center(i+1,centerName,centerAddress,noofrooms,scap,freq,type);
                fcenterList.add(center);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fcenterList;
    }
}
