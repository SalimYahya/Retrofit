package com.example.retrofit.utils;

public interface RegistrationContract {
    interface View{
        String getFirstName();
        String getLastName();
        String getUsername();
        String getPassword();

        void showErrorFirstName();
        void showErrorLastName();
        void showErrorUsername();
        void showErrorPassword();

        void showProgress();
        void hideProgress();

        void navigateToHome();

        void listenToInputFields();
        void showToast(String strg);
    }

    interface  Presenter{
        void onRegisterClick();
    }

    interface Interactor{
       interface OnFinishedListener{
           void onFinished(int result); // 0 --> Error, 1 --> user registered, 2 --> user already exist
       }


       void register(String firstName,
                     String lastname,
                     String username,
                     String password,
                     OnFinishedListener listener);
    }
}
