package com.example.retrofit.utils;

import com.example.retrofit.model.user.User;

public interface ProfileContract {

    interface View{
        String getFirstName();
        String getLastName();
        String getPassword();
        String getConfirmPassword();
        String getUsername();

        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setUsername(String username);

        void showErrorFirstName();
        void showErrorLastName();
        void showErrorPassword();
        void showConfirmPasswordError(String strg);

        void showProgress();
        void hideProgress();

        void showOnError(String s);
        void showOnSuccess(String s);
    }

    interface Presenter{
        void onSubmitClick();
        void getUser(String username);
    }

    interface ProfileInteractor{
        interface OnFinishedListener{
            void onGetUserFinished(User user, String strg );
        }

        void getUser(String username, OnFinishedListener listener);


        void update(
                String firstName,
                String lastName,
                String username,
                String password,
                OnFinishedListener listener
        );
    }
}
