package com.example.retrofit.utils;

public interface LoginContract {

    interface LoginView{

        String getUsername();
        String getPassword();

        void showUsernameError();
        void showPasswordError();

        void showProgress();
        void hideProgress();

        void showUserNotFound();
        void navigateToHome();
    }

    interface LoginPresenter{
        void onLoginClicked();
    }

    interface LoginInteractor{
        interface OnFinishedListener{
            void onFinished(boolean isValidUser, String message);
        }

        void login(String username, String password, OnFinishedListener listener);
    }
}
