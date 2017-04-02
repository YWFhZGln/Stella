package com.higgsontech.stella.Utils;

import android.content.SharedPreferences;

/**
 * Created by GUR23835 on 4/1/2017.
 */


public class Constants {
    public static final String loginResponse = "lResponse";
    public static final String id = "id";
    public static final String fname = "fname";
    public static final String lname = "lname";
    public static final String designation = "designation";
    public static final String permission = "permissions";
    public static final String isLoggedIn = "isLoggedIn";
    public static final String aadhar = "aadhar";
    public static final String office_pin = "office_pin";
    public static final String home_pin = "home_pin";
    public static final String signup_email = "email";
    public static final String signup_password = "password";
    public static final String mobile = "mobile";
    public static final String email = "email";
    public static final String password = "password";



    /*Officer Roles*/
    public static final String ROLE_CENTRAL_OFFICER = "Central Officer";
    public static final String ROLE_ZONAL_OFFICER = "Zonal Officer";
    public static final String ROLE_OSDS = "Officers on Special Duties";
    public static final String ROLE_EXAM_CENTER_HEAD = "Examination Center Head";
    public static final String ROLE_INVIGILATOR = "Invigilators";

    public static final int INT_ROLE_CENTRAL_OFFICER = 5;
    public static final int INT_ROLE_ZONAL_OFFICER = 4;
    public static final int INT_ROLE_OSDS = 3;
    public static final int INT_ROLE_EXAM_CENTER_HEAD = 2;
    public static final int INT_ROLE_INVIGILATOR = 1;

    public static final String PERMISSIONS_URL="";
    public static final String PERMISSION_PENDING=null;



    private static SharedPreferences sharedpreferences = null;

    public static void setSharedpreferences(SharedPreferences preferences) {
        sharedpreferences = preferences;
    }

    public static void updateSharedPrefence(String key, String value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void updateSharedPrefence(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String fetchSharedPreferenceValues(String key) {
        return sharedpreferences.getString(key, "NULL");
    }

    public static Boolean fetchSharedPreferenceValues(String key, boolean isBoolean) {
        return sharedpreferences.getBoolean(key, false);
    }
}
