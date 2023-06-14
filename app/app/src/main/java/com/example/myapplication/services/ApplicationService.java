package com.example.myapplication.services;

import android.content.Context;

import com.google.gson.Gson;


public class ApplicationService {

    private static ApplicationService instance;

    private Context context;

    public Gson gson;

    private ApplicationService() {
        this.gson = new Gson();
    }

    public static synchronized ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

