package com.example.retrofit.presenter;

import com.example.retrofit.model.user.User;
import com.example.retrofit.model.user.UserRepositoryImpl;
import com.example.retrofit.utils.ProfileContract;

public class ProfilePresenterImpl implements ProfileContract.Presenter, ProfileContract.ProfileInteractor.OnFinishedListener {

    ProfileContract.View view;
    UserRepositoryImpl repository;

    public ProfilePresenterImpl(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void onSubmitClick() {
        if (view != null) {

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


            if (view.getPassword().isEmpty()){
                view.hideProgress();
                view.showErrorPassword();
                return;
            }


            if (view.getConfirmPassword().isEmpty()){
                view.hideProgress();
                view.showConfirmPasswordError("Must Not Be Empty");
                return;
            }

            if (!view.getPassword().equals(view.getConfirmPassword())){
                view.hideProgress();
                view.showConfirmPasswordError("Password Doesn't Match");
                return;
            }

            repository = new UserRepositoryImpl();
            repository.update(view.getFirstName(), view.getLastName(), view.getPassword(), view.getUsername(), this);
        }

    }

    @Override
    public void getUser(String username) {
        if (view != null) {
            view.showProgress();
            repository = new UserRepositoryImpl();
            repository.getUser(username, this);
        }
    }

    @Override
    public void onGetUserFinished(User user, String message) {
        if (view != null){
            view.hideProgress();

            if (user != null){
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
                view.setUsername(user.getUsername());
                view.showOnSuccess(message);
                return;

            }else {
                view.showOnError(message);
            }
        }
    }
}
