package com.example.zaidsaeed.tipcalculator;

import android.app.Application;

public class MyApplication extends Application {
    private Integer defaultTip;

    private String defaultCurrency = "Dollar";

    public Integer getDefaultTip(){
        return this.defaultTip;
    }

    public void setDefaultTip(Integer defaultTip){
        this.defaultTip = defaultTip;
    }

    public String getDefaultCurrency(){
        return this.defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency){
        this.defaultCurrency = defaultCurrency;
    }

}
