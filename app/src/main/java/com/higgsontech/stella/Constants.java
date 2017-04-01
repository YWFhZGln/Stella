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
    public static final String permission = "permissions";

    /*Officer Roles*/
    public static final String ROLE_CENTRAL_OFFICER = "Central Officer";
    public static final String ROLE_ZONAL_OFFICER = "Zonal Officer";
    public static final String ROLE_OSDS = "Officers on Special Duties";
    public static final String ROLE_EXAM_CENTER_HEAD = "Examination Center Head";
    public static final String ROLE_INVIGILATOR = "Invigilators";


    private static SharedPreferences sharedpreferences = null;

    public static void setSharedpreferences(SharedPreferences preferences){
        sharedpreferences = preferences;
    }
    public static void  updateSharedPrefence(String key, String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void  updateSharedPrefence(String key, Boolean value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String fetchSharedPreferenceValues(String key){
        return sharedpreferences.getString(key,"NULL");
    }

    public static Boolean fetchSharedPreferenceValues(String key, boolean isBoolean){
        return sharedpreferences.getBoolean(key,false);
    }
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
