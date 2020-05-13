package com.example.retrofit.presenter;

import android.util.Log;

import com.example.retrofit.model.user.UserRepositoryImpl;
import com.example.retrofit.utils.LoginContract;

public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginContract.LoginInteractor.OnFinishedListener{

    LoginContract.LoginView view;
    UserRepositoryImpl repository;

    public LoginPresenterImpl(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void onLoginClicked() {

        if (view.getUsername().isEmpty()) {
            view.hideProgress();
            view.showUsernameError();
            return;
        }

        if (view.getPassword().isEmpty()) {
            view.hideProgress();
            view.showPasswordError();
            return;
        }

        view.showProgress();

        repository = new UserRepositoryImpl();
        repository.login(view.getUsername(), view.getPassword(), this);
    }

    @Override
    public void onFinished(boolean isValidUser, String message) {
        view.hideProgress();

        if (isValidUser) {
            view.navigateToHome();
        }

        if (!isValidUser){
            view.showUserNotFound();
            Log.d(LoginPresenterImpl.class.getSimpleName(), message);
        }
    }

    public class LoginCredentials{

        String username;
        String password;

        public LoginCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
