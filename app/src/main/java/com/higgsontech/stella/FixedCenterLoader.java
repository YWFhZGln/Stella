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
        List<Center> fCList=parse(response);

        return fCList;
    }

    private static List<Center> parse(String res) {

        List<Center> fcenterList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject currentId = jsonArray.getJSONObject(i);
                String placeId = currentId.getString("placeId");
                Log.e(LOG_TAG, placeId);
                Center center = new Center(i + 1, placeId);
                fcenterList.add(center);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fcenterList;
    }
}
