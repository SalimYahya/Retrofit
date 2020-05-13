package com.example.retrofit.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.retrofit.R;
import com.example.retrofit.utils.NavigateTo;

public class DashBoardActivity extends AppCompatActivity implements NavigateTo {
    private static final String NAME_ARGS = "name";
    private static final String TAG = DashBoardActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (savedInstanceState == null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    replaceFragment(DashBoardFragment.newInstance("DashBoardFragment"), TAG, true);
                }
            }, 1200);
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
            //finish();
        }else{
            super.onBackPressed();
        }
    }

}
