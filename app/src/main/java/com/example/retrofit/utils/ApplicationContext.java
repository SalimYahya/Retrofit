package com.example.retrofit.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;

import androidx.annotation.NonNull;

public class ApplicationContext extends Application {

    private static ApplicationContext instance;
    private static Context context;
    private boolean isConnected = false;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }

        ApplicationContext.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return ApplicationContext.context;
    }

    public static ApplicationContext getInstance(){
        return instance;
    }

    public static boolean hasNetwork(){
        return instance.isNetworkConnected();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                isConnected = true;
            }

        });

        return isConnected;
    }

    /*private static Context context;

    public static Context getAppContext(){
        return ApplicationContext.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext.context = getApplicationContext();
    }*/

}
