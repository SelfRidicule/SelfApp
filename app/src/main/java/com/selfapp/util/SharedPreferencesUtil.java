package com.selfapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.selfapp.BuildConfig;

import java.util.Map;

/**
 *
 */
public class SharedPreferencesUtil {
    /**
     * =================================================================================
     */
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static SharedPreferencesUtil sharedPreferencesUtil;
    private Context context;
    /**
     * =================================================================================
     */
    public static String Self_PACKGNAME = BuildConfig.APPLICATION_ID;
    public static String Self_ID = "user_id";

    /**
     * =================================================================================
     */

    public SharedPreferencesUtil(Context context) {
        this.context = context.getApplicationContext();
        sharedPreferences =   this.context.getSharedPreferences(Self_PACKGNAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sharedPreferencesUtil ==null){
            sharedPreferencesUtil =new SharedPreferencesUtil(context);
        }
        return  sharedPreferencesUtil;
    }


    public static void setStringPreferences(Context context, String key, String value) {
        getInstance(context).editor.putString(key, value).commit();
    }

    public static void setBooleanPreferences(Context context, String key, Boolean value) {
        getInstance(context).editor.putBoolean(key, value).commit();
    }

    public static void setStringPreferences(Context context, Map<String, String> vlues) {
        for (String key : vlues.keySet()) {
            getInstance(context).editor.putString(key, vlues.get(key)).commit();
        }
    }

    public static String getStringPreference(Context context, String key, String defaultValue) {
        return getInstance(context).sharedPreferences.getString(key, defaultValue);
    }

    public static boolean getBooleanPreference(Context context, String key, boolean defaultValue) {
        return getInstance(context).sharedPreferences.getBoolean(key, defaultValue);
    }


}
