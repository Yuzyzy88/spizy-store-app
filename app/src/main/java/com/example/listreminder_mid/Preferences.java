package com.example.listreminder_mid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Preferences {
    // declare data key
    static final String key_user_regis = "user_regis";
    static final String key_pass_regis = "pass_regis";
    static final String key_id_regis = "id_regis";

    static final String key_id_login = "id_login";
    static final String key_user_login = "user_login";
    static final String key_status_login = "status_login";

    // declare shared preferences
    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // set registered user
    public static void setRegisteredUser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key_user_regis, username);
        editor.apply();
    }

    // get registered user
    public static String getRegisteredUser(Context context) {
        return getSharedPreference(context).getString(key_user_regis, "");
    }

    // set register id
    public static void setRegisteredId(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key_id_regis, username);
        editor.apply();
    }

    // get register id
    public static String getRegisteredId(Context context) {
        return getSharedPreference(context).getString(key_id_regis, "");
    }

    // set register pass
    public static void setRegisteredPass(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key_pass_regis, password);
        editor.apply();
    }

    // get register pass
    public static String getRegisteredPass(Context context){
        return getSharedPreference(context).getString(key_pass_regis,"");
    }

    // set login user
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key_user_login, username);
        editor.apply();
    }

    // get login user
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(key_user_login,"");
    }

    // set login Id
    public static void setLoggedInId(Context context, String userId){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key_id_login, userId);
        editor.apply();
    }

    // get login Id
    public static String getLoggedInId(Context context){
        return getSharedPreference(context).getString(key_id_login,"");
    }

    // set status login
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key_status_login,status);
        editor.apply();
    }

    // get status login
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(key_status_login,false);
    }

    /* Edit Preferences declaration and delete data, so that makes it the default value
     * specifically for data that has the key */
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(key_user_login);
        editor.remove(key_status_login);
        editor.apply();
    }

}
