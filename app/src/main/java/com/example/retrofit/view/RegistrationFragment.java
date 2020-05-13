package com.example.retrofit.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.retrofit.R;
import com.example.retrofit.presenter.RegistrationPresenterImpl;
import com.example.retrofit.utils.RegistrationContract;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    TextInputLayout inpt_firstName, inpt_lastName, inpt_username, inpt_password;
    TextInputEditText ed_firstName, ed_lastName, ed_username, ed_password;
    MaterialButton btn_submit;
    ProgressBar progressBar;

    RegistrationContract.Presenter presenter;

    private static final String NAME_ARGS = "name";
    private static final String TAG = "RegistrationFragment";

    private String mName;

    public static RegistrationFragment newInstance(final String name){
        final RegistrationFragment registrationFragment = new RegistrationFragment();
        Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        registrationFragment.setArguments(args);

        return registrationFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();

        if (args == null || !args.containsKey(NAME_ARGS)){
            Log.d(TAG, "args is empty or null");
        }else{
            mName = args.getString(NAME_ARGS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initiateView(view);
        presenter = new RegistrationPresenterImpl(this);
        ed_password.setOnKeyListener(inputFieldsListener);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterClick();
            }
        });
        return view;
    }

    /**
     * initiateView() : To initiate {@link RegistrationFragment fields} TextView etc.
     * */
    public void initiateView(View view) {

        inpt_firstName = view.findViewById(R.id.inpt_first_name);
        ed_firstName = view.findViewById(R.id.ed_first_name);

        inpt_lastName = view.findViewById(R.id.inpt_last_name);
        ed_lastName = view.findViewById(R.id.ed_last_name);

        inpt_username = view.findViewById(R.id.inpt_username);
        ed_username = view.findViewById(R.id.ed_username);

        inpt_password = view.findViewById(R.id.inpt_password);
        ed_password = view.findViewById(R.id.ed_password);
        btn_submit = view.findViewById(R.id.btn_submit);

        progressBar = view.findViewById(R.id.progress_bar);
    }


    /**
     * Listens to inputs and clear error
     * TODO: Fix inputFieldsListener
     * */
    View.OnKeyListener inputFieldsListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ( ed_password.getText() != null) {
                inpt_password.setError(null);
            }
            return false;
        }
    };

    @Override
    public void listenToInputFields(){
        inpt_password.setOnKeyListener(inputFieldsListener);
    }


    /**
     * Implements {@link RegistrationContract.View} methods
     * */
    @Override
    public String getFirstName() {
        return ed_firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return ed_lastName.getText().toString();
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
    public void showErrorFirstName() {
        inpt_firstName.setError("Must not be empty");
    }

    @Override
    public void showErrorLastName() {
        inpt_lastName.setError("Must not be empty");
    }

    @Override
    public void showErrorUsername() {
        inpt_username.setError("Must not be empty");
    }

    @Override
    public void showErrorPassword() {
        inpt_password.setError("Must be more then 8");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateToHome() {

    }

    @Override
    public void showToast(String strg){
        Toast.makeText(getActivity(),strg,Toast.LENGTH_SHORT).show();
    }

    /**
     * Implements {@link Fragment} lifecycle methods
     * */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        return super.onGetLayoutInflater(savedInstanceState);
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
