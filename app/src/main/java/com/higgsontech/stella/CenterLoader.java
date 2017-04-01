package com.higgsontech.stella;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 29/3/17.
 */

public class CenterLoader extends AsyncTaskLoader<List<Center>> {

    public static final String LOG_TAG = CenterLoader.class.getSimpleName();
    private String url;
    private String loaderId;
    public CenterLoader(Context context,String u,String lId) {
        super(context);
        this.url=u;
        this.loaderId=lId;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Center> loadInBackground() {
        Log.e(LOG_TAG,"loadInBackground called");

        List<Center> centers=getCenterList(url);
        return centers;
    }

    private List<Center> getCenterList(String mUrl){

        Log.e(LOG_TAG,"getCenterList called");
        String jsonResponse=makeHttpRequest(mUrl);
        List<Center> centerList=parseJson(jsonResponse);
        return centerList;


    }

    private List<Center> parseJson(String jsonResponse) {

        if(TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<Center> centerList = new ArrayList<>();

        try {

            JSONObject baseJSONResponse=new JSONObject(jsonResponse);

            JSONArray centerArray=baseJSONResponse.getJSONArray("results");

            for (int i=0;i<centerArray.length();i++){
                JSONObject currentCenter=centerArray.getJSONObject(i);

                String name=currentCenter.getString("name");
                String placeId=currentCenter.getString("place_id");
                if(loaderId=="5") {

                  //  Center center=new Center(i+1,name,"",placeId);
                   // centerList.add(center);
                   // String address = currentCenter.getString("formatted_address");
                    JSONObject geometry=currentCenter.getJSONObject("geometry");
                    JSONObject location=geometry.getJSONObject("location");

                    double lat=location.getDouble("lat");
                    double  lng=location.getDouble("lng");

                    Center center=new Center(i+1,name,"",placeId,lat,lng);
                    centerList.add(center);




                }
                else {
                    String address = currentCenter.getString("formatted_address");
                    JSONObject geometry=currentCenter.getJSONObject("geometry");
                    JSONObject location=geometry.getJSONObject("location");

                    double lat=location.getDouble("lat");
                    double  lng=location.getDouble("lng");

                    Center center=new Center(i+1,name,address,placeId,lat,lng);
                    centerList.add(center);


                }


            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return centerList;
    }

    private String makeHttpRequest(String urlString){

        URL url=null;
        try {
            url=new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String jsonResponse = "";
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection=null;
        try {
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);

            inputStream=httpURLConnection.getInputStream();
            jsonResponse=readFromStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) {
        StringBuilder output=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String line= null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (line!=null){
                output.append(line);
                try {
                    line=reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Log.e("json",output.toString());
        return output.toString();
    }


}
