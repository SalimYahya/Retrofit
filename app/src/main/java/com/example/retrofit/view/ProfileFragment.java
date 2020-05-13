package com.example.retrofit.view;

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

import com.example.retrofit.R;
import com.example.retrofit.presenter.ProfilePresenterImpl;
import com.example.retrofit.utils.ApplicationContext;
import com.example.retrofit.utils.ProfileContract;
import com.example.retrofit.utils.SharedPrefManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileFragment extends Fragment implements ProfileContract.View {


    TextInputLayout inpt_firstName, inpt_lastName, inpt_password, inpt_confirmPassword;
    TextInputEditText ed_firstName, ed_lastName, ed_username, ed_password, ed_confirmPassword;
    MaterialButton btn_submit;
    ProgressBar progressBar;

    ProfileContract.Presenter presenter;

    private static final String NAME_ARGS = "name";
    private static final String TAG = "ProfileFragment";
    String mName;

    public static ProfileFragment newInstance(final String name){
        final ProfileFragment profileFragment = new ProfileFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        profileFragment.setArguments(args);

        return profileFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final Bundle args = getArguments();
        if (args == null || !args.containsKey(NAME_ARGS)){
            Log.d(TAG, "args is null or empty");
        }else {
            mName = args.getString(NAME_ARGS);
            Log.d(TAG, mName);
            Log.d(TAG, "onCreate()");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setUpToolBar(view);
        initiateView(view);

        presenter = new ProfilePresenterImpl(this);
        presenter.getUser(SharedPrefManager.getInstance(ApplicationContext.getAppContext()).getUsername());

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmitClick();
            }
        });

        return view;
    }

    private void setUpToolBar(View view) {
    }

    private void initiateView(View view) {

        inpt_firstName = view.findViewById(R.id.inpt_first_name);
        ed_firstName = view.findViewById(R.id.ed_first_name);

        inpt_lastName = view.findViewById(R.id.inpt_last_name);
        ed_lastName = view.findViewById(R.id.ed_last_name);

        ed_username = view.findViewById(R.id.ed_username);

        inpt_password = view.findViewById(R.id.inpt_password);
        ed_password = view.findViewById(R.id.ed_password);

        inpt_confirmPassword = view.findViewById(R.id.inpt_confirm_password);
        ed_confirmPassword = view.findViewById(R.id.ed_confirm_password);

        btn_submit = view.findViewById(R.id.btn_submit);

        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public String getFirstName() {
        return ed_firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return ed_lastName.getText().toString();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return ed_confirmPassword.getText().toString();
    }

    @Override
    public String getUsername() {
        return SharedPrefManager
                .getInstance(ApplicationContext.getAppContext())
                .getUsername();
    }

    @Override
    public void setFirstName(String firstName) {
        ed_firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        ed_lastName.setText(lastName);
    }

    @Override
    public void setUsername(String username) {
        ed_username.setEnabled(true);
        ed_username.setText(username);
        ed_username.setEnabled(false);
    }

    @Override
    public void showErrorFirstName() {
        inpt_firstName.setError("Must not be Empty");
    }

    @Override
    public void showErrorLastName() {
        inpt_lastName.setError("Must not be Empty");
    }

    @Override
    public void showErrorPassword() {
        inpt_password.setError("Enter Password");
    }

    @Override
    public void showConfirmPasswordError(String errorMessage) {
        inpt_confirmPassword.setError(errorMessage);
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
    public void showOnSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOnError(String error) {
        Toast.makeText(getActivity(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
    }
}
