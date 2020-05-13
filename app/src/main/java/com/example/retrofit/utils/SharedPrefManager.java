package com.example.retrofit.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager instance;
    private Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "pref_file";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_ID = "id";

    private SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (instance == null)
            instance = new SharedPrefManager(context);

        return instance;
    }

    /**
     * When Login, Save Username
     * */
    public void userLogin(String username){
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    /**
     * When app lunches, check Username name is saved
     * */
    public boolean isUserLoggedIn(){
        if (sharedPreferences.getString(KEY_USERNAME, null) != null)
            return true;

        return false;
    }


    /**
     * When needed, get username
     * */
    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    /**
     * When logout, clear Username
     * */
    public void logout(){
        editor.clear();
        editor.apply();
    }
}
