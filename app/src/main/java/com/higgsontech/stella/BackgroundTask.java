package com.higgsontech.stella;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundTask extends AsyncTask<String, Void, String> {


    private Context ctx;
    private AlertDialog alertDialog, signupAlertDialog;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
        signupAlertDialog = new AlertDialog.Builder(ctx).create();
        signupAlertDialog.setTitle("Sign Up Information");
    }

    @Override
    protected String doInBackground(String... params) {

        String login_url = "http://higgsontech.com/hack/login.php";
        String signup_url = "http://higgsontech.com/hack/register.php";
        String method = params[0];
        if (method.equals("register")) {
            String u_fname, u_lname, u_gender, u_aadhar, u_designation, u_office_pin, u_home_pin, u_signup_email, u_signup_password, u_mobile;
            u_fname = params[1];
            u_lname = params[2];
            u_gender = params[3];
            u_aadhar = params[4];
            u_designation = params[5];
            u_office_pin = params[6];
            u_home_pin = params[7];
            u_signup_email = params[8];
            u_signup_password = params[9];
            u_mobile = params[10];

            try {
                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(u_fname, "UTF-8") + "&" +
                                URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(u_lname, "UTF-8") + "&" +
                                URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(u_gender, "UTF-8") + "&" +
                                URLEncoder.encode("aadhar", "UTF-8") + "=" + URLEncoder.encode(u_aadhar, "UTF-8") + "&" +
                                URLEncoder.encode("designation", "UTF-8") + "=" + URLEncoder.encode(u_designation, "UTF-8") + "&" +
                                URLEncoder.encode("office_pin", "UTF-8") + "=" + URLEncoder.encode(u_office_pin, "UTF-8") + "&" +
                                URLEncoder.encode("home_pin", "UTF-8") + "=" + URLEncoder.encode(u_home_pin, "UTF-8") + "&" +
                                URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(u_signup_email, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(u_signup_password, "UTF-8") + "&" +
                                URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(u_mobile, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Succes...";


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("login")) {
            String u_email, u_password;
            u_email = params[1];
            u_password = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(u_email, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(u_password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    Log.v("==========response", line);
                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                Constants.updateSharedPrefence(Constants.loginResponse, response);
                JSONObject responseJson=(new JSONObject(response));
                Constants.updateSharedPrefence(Constants.id, responseJson.getString(Constants.id));
                Constants.updateSharedPrefence(Constants.fname, responseJson.getString(Constants.fname));
                Constants.updateSharedPrefence(Constants.lname, responseJson.getString(Constants.lname));
                Constants.updateSharedPrefence(Constants.designation, responseJson.getString(Constants.designation));
                //Constants.updateSharedPrefence(Constants.permission, responseJson.getBoolean(Constants.permission));
                Constants.updateSharedPrefence(Constants.permission, false);
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }

        }
        return "y";
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration Succes...")) {

            Intent i = new Intent(ctx, Login.class);
            ctx.startActivity(i);
            signupAlertDialog.setMessage("Sign up success... Please Login");
            signupAlertDialog.setIcon(R.drawable.success);
            signupAlertDialog.show();
        } else {
            if (result.equals("Y")) {
                alertDialog.setMessage("Login Success");
                alertDialog.setIcon(R.drawable.success);
                alertDialog.show();

                Intent i = new Intent(ctx, Dashboard.class);
                ctx.startActivity(i);
            } else {
                alertDialog.setMessage("Invalid Username or Password! ");
                alertDialog.setIcon(R.drawable.fail);
                alertDialog.show();
            }
        }
    }
}
