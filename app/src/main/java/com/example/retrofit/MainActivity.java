package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.retrofit.utils.ApplicationContext;
import com.example.retrofit.utils.NavigateTo;
import com.example.retrofit.utils.SharedPrefManager;
import com.example.retrofit.view.DashBoardActivity;
import com.example.retrofit.view.LoginFragment;


public class MainActivity extends AppCompatActivity implements NavigateTo {

    private static int SPLASH_TIME_OUT = 4000;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        if (savedInstanceState == null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (SharedPrefManager.getInstance(ApplicationContext.getAppContext()).isUserLoggedIn()){
                        startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                        finish();
                    }else{
                        replaceFragment(LoginFragment.newInstance("LoginFragment"), "LoginFragment", true);
                    }
                }
            }, SPLASH_TIME_OUT);
        }
    }


    @Override
    public void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (currentFragment != null){
            if (currentFragment.getClass() == fragment.getClass()){
                return;
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(tag) != null){
            fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container, fragment, tag);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag);

        // Remove this Log and for-loop Log in future
        Log.d(TAG, "Fragment Stack Count: " + fragmentManager.getBackStackEntryCount());
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++){
            Log.d(TAG, "Fragment Stack Count: " + fragmentManager.getBackStackEntryAt(i).getName());
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int fragmentInStack = getSupportFragmentManager()
                .getBackStackEntryCount();

        if (fragmentInStack > 1){
            getSupportFragmentManager().popBackStack();
        }else if (fragmentInStack == 1){
            finish();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }
}