package com.higgsontech.stella;

import android.content.SharedPreferences;

/**
 * Created by GUR23835 on 4/1/2017.
 */


public class Constants {
    //public  static final String Login = "Login";
    //public static final String MyPREFERENCES = "MyPREFERENCES";
    public static final String Email = "email" ;
    public static final String Passwd = "password" ;
    public static final String loginResponse = "lResponse";
    public static final String id = "id";
    public static final String fname = "fname";
    public static final String lname = "lname";
    public static final String designation = "designation";

    private static SharedPreferences sharedpreferences = null;

    public static void setSharedpreferences(SharedPreferences preferences){
        sharedpreferences = preferences;
    }
    public static void  updateSharedPrefence(String key, String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String fetchSharedPreferenceValues(String key){
        return sharedpreferences.getString(key,"NULL");
    }
}
