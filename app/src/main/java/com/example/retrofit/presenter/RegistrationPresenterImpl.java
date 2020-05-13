package com.example.retrofit.presenter;

import com.example.retrofit.model.user.UserRepositoryImpl;
import com.example.retrofit.utils.RegistrationContract;

public class RegistrationPresenterImpl implements RegistrationContract.Presenter, RegistrationContract.Interactor.OnFinishedListener {

    RegistrationContract.View view;
    UserRepositoryImpl repository;

    public RegistrationPresenterImpl(RegistrationContract.View view) {
        this.view = view;
        repository = new UserRepositoryImpl();
    }

    @Override
    public void onRegisterClick() {
        if (view.getFirstName().isEmpty()){
            view.hideProgress();
            view.showErrorFirstName();
            return;
        }

        if (view.getLastName().isEmpty()){
            view.hideProgress();
            view.showErrorLastName();
            return;
        }

        if (view.getUsername().isEmpty()){
            view.hideProgress();
            view.showErrorUsername();
            return;
        }

        if (view.getPassword().isEmpty()){
            view.hideProgress();
            view.showErrorPassword();
            return;
        }

        view.showProgress();
        repository.register(view.getFirstName(), view.getLastName(), view.getUsername(), view.getPassword(), this);
    }

    @Override
    public void onFinished(int isRegistered) {
        view.hideProgress();
        String strg = "Status: ";

        if (isRegistered == 2){
            strg += "User Already Exist, retrieve your password";
        } else if (isRegistered == 1) {
            strg += "Registered Successfully";
        } else{
            strg += "Not Registered";
        }

        view.showToast(strg);
    }

}
