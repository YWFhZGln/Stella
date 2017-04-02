package com.higgsontech.stella;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by aman on 28/3/17.
 */

public class AuthLoader extends AsyncTaskLoader<String> {
    public static final String LOG_TAG = AuthLoader.class.getSimpleName();


    private String mUrl;
    private String mUid;
    public AuthLoader(Context context,String url,String uid) {
        super(context);
        mUrl=url;
        mUid=uid;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        String inputXml=AuthUtils.createXmlInput(mUid);
        String response=makeHttpRequest(mUrl,inputXml);
        return response;
    }

    private String makeHttpRequest(String address,String xml)  {
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream=null;
        String rs="";


        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
//                httpURLConnection.setRequestProperty("Content-type", "application/xml");
//                httpURLConnection.connect();
            Log.e(LOG_TAG, "connect called");


            OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
//                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
//                bufferedWriter.write(s);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();

            outputStream.write(xml.getBytes());
            outputStream.flush();
            outputStream.close();

            inputStream =httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            Log.i(LOG_TAG,out.toString());

           rs = parseXmlResponse(out.toString());
            Log.i(LOG_TAG,"xml parsed");




          //  int responsecode = httpURLConnection.getResponseCode();

              reader.close();

        } catch (IOException e) {
            Log.e(LOG_TAG, "not connected");
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return rs;
    }

    private String parseXmlResponse(String xml)  {
        String ret = "";
        String err;

        String response = "";

        XmlPullParserFactory parserFactory = null;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            XmlPullParser xpp = parserFactory.newPullParser();
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xpp.setInput(new StringReader(xml));
            int event = xpp.getEventType();
           // Log.i(LOG_TAG,String.valueOf(event));
            while (event != XmlPullParser.END_DOCUMENT) {
               // Log.i(LOG_TAG,String.valueOf(event));
                switch (event) {
                    case XmlPullParser.START_TAG:
                        String tagname = xpp.getName();
                        Log.i(LOG_TAG,tagname);

                        if (tagname.equals("AuthRes")) {
                            ret = xpp.getAttributeValue(null, "ret");
                            if ("y".equals(ret)) {
                                response = "Matched";
                            } else {
                                err =xpp.getAttributeValue(null, "err");
                                Log.i(LOG_TAG,err);
                                response = "Not Matched/ error=" + err;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        break;

                }
                event = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.i(LOG_TAG,response);

        return response;

    }
}
