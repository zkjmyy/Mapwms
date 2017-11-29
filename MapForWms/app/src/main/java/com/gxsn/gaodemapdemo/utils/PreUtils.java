package com.gxsn.gaodemapdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

//SharePreference的封装
public class PreUtils {

    public static boolean getBoolean(Context ctx, String key, boolean defValue){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        return sp.getBoolean(key,defValue);

    }

    public static void  setBoolean(Context ctx, String key, boolean value){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setString(Context ctx, String key, String value){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        sp.edit().putString(key, value).commit();
    }


    public static String getString(Context ctx, String key, String deFvalue){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        return sp.getString(key,deFvalue);
    }

    public static void setInt(Context ctx, String key, int value){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        sp.edit().putInt(key, value).commit();

    }

    public static int getInt(Context ctx, String key, int deFvalue){
        SharedPreferences sp=ctx.getSharedPreferences("config", Context.MODE_APPEND);
        return sp.getInt(key, deFvalue);

    }
}
