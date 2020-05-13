package com.example.retrofit.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.retrofit.R;
import com.google.android.material.card.MaterialCardView;

public class DashBoardFragment extends Fragment  {

    MaterialCardView profileCardView, storeCardView;

    private static final String NAME_ARGS = "name";
    private static final String TAG = "DashBoardFragment";

    public static DashBoardFragment newInstance(final String name){
        final DashBoardFragment dashBoardFragment = new DashBoardFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        dashBoardFragment.setArguments(args);

        return dashBoardFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // My Code Goes Here
        final Bundle args = getArguments();
        if (args == null || !args.containsKey(NAME_ARGS)){
            Log.d(TAG, "args is null or empty");
        }else{
            String mName = args.getString(NAME_ARGS);
            Log.d(TAG, mName);
            Log.d(TAG,"onCreate()");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_grid, container, false);
        setUpToolBar(view);
        initiateView(view);


        profileCardView.setOnClickListener(click);
        storeCardView.setOnClickListener(click);

        return view;
    }

    private void setUpToolBar(View view) {

        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null){
            toolbar.setTitle(activity.getTitle().toString() + " - Dashboard");
            activity.setSupportActionBar(toolbar);
        }
    }

    View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.profile:
                    ((DashBoardActivity) getActivity()).replaceFragment(ProfileFragment.newInstance("ProfileFragment"), TAG, true);
                    break;

                case R.id.store:
                    ((DashBoardActivity) getActivity()).replaceFragment(ProductsFragment.newInstance("ProductsFragment"), TAG, true);
                    break;
            }
        }
    };

    /**
     * initiateView() : To initiate {@link DashBoardFragment fields} TextView etc.
     * */
    public void initiateView(View view){
        profileCardView = view.findViewById(R.id.profile);
        storeCardView = view.findViewById(R.id.store);
    }


    /*@Override
    public void navigateToHome() {

        // Save Username to SharedPreferences
        SharedPrefManager.getInstance(
                ApplicationContext.getAppContext()).userLogin(getUsername());

        // Todo: Clear the old backstack

        Toast.makeText(getActivity(), "Logged In Successfully", Toast.LENGTH_SHORT).show();

        // Implements Navigation
        ((MainActivity) getActivity())
                .replaceFragment(DashBoardActivity.newInstance("DashBoardFragment"),
                        TAG,
                        true);


        getActivity().startActivity(new Intent(getActivity(), DashBoardActivity.class));
        getActivity().finish();
    }*/


    /**
     * Implements {@link Fragment} lifecycle methods
     * */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach()");
    }

}
