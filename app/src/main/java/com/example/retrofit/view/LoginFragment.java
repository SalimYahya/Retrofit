package com.example.retrofit.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.retrofit.MainActivity;
import com.example.retrofit.R;
import com.example.retrofit.presenter.LoginPresenterImpl;
import com.example.retrofit.utils.ApplicationContext;
import com.example.retrofit.utils.LoginContract;
import com.example.retrofit.utils.SharedPrefManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LoginContract.LoginView {

    TextInputLayout inpt_username, inpt_password;
    TextInputEditText ed_username, ed_password;
    MaterialButton btn_login, btn_forgotPassword, btn_register;
    ProgressBar progressBar;
    LoginContract.LoginPresenter presenter;

    private static final String NAME_ARGS = "name";
    private static final String TAG = "LoginFragment";

    public static LoginFragment newInstance(final String name){
        final LoginFragment loginFragment = new LoginFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        loginFragment.setArguments(args);

        return loginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initiateView(view);
        presenter = new LoginPresenterImpl(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClicked();
                Log.d(TAG,"presenter.onLoginClicked()");

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity())
                        .replaceFragment(
                                RegistrationFragment.newInstance("Registration Fragment"),
                                "Registration Fragment",
                                true);
                Log.d(TAG,"btn_register clicked");
            }
        });
        return view;
    }

    /**
     * initiateView() : To initiate {@link LoginFragment fields} TextView etc.
     * */
    public void initiateView(View view){

        inpt_username = view.findViewById(R.id.inpt_username);
        ed_username = view.findViewById(R.id.ed_username);

        inpt_password = view.findViewById(R.id.inpt_password);
        ed_password = view.findViewById(R.id.ed_password);

        progressBar = view.findViewById(R.id.progress_bar);
        btn_register = view.findViewById(R.id.btn_register);
        btn_forgotPassword = view.findViewById(R.id.btn_forget_password);
        btn_login = view.findViewById(R.id.btn_login);
    }

    /**
     * Implements {@link LoginContract.LoginView} methods
     * */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return ed_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString();
    }

    @Override
    public void showUsernameError() {
        inpt_username.setError("Insert Username");
    }

    @Override
    public void showPasswordError() {
        inpt_password.setError("Insert Password");
    }

    @Override
    public void showUserNotFound(){
        Toast.makeText(getActivity(), "Username/Password are wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {

        // Save Username to SharedPreferences
        SharedPrefManager.getInstance(
                ApplicationContext.getAppContext()).userLogin(getUsername());

        Toast.makeText(getActivity(), "Logged In Successfully", Toast.LENGTH_SHORT).show();

        getActivity().startActivity(new Intent(getActivity(), DashBoardActivity.class));
        getActivity().finish();
    }


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
