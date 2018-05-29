package com.example.zaidsaeed.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context context;

    PrefManager(Context context) {
        this.context = context;
    }

    private Integer defaultTip;

    private String defaultCurrency = "Dollar";

    public Integer getDefaultTip(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DefaultTip", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("tip", 0);
    }

    public void setDefaultTip(Integer defaultTip){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DefaultTip", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tip", defaultTip);
        editor.commit();
    }

    public String getDefaultCurrency(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DefaultCurrency", Context.MODE_PRIVATE);
        return sharedPreferences.getString("default_currency", "");
    }

    public void setDefaultCurrency(String defaultCurrency){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DefaultCurrency", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("default_currency", defaultCurrency);
        editor.commit();
    }


}