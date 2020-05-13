package com.example.retrofit.model.user;


import android.os.Handler;
import android.util.Log;

import com.example.retrofit.model.user.User;
import com.example.retrofit.model.user.UserRepository;
import com.example.retrofit.utils.LoginContract;
import com.example.retrofit.utils.ProfileContract;
import com.example.retrofit.utils.RegistrationContract;
import com.example.retrofit.utils.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements RegistrationContract.Interactor, LoginContract.LoginInteractor, ProfileContract.ProfileInteractor {

    private static final String TAG = "UserRepositoryImpl";
    private static final int TIME_OUT = 1200;
    private UserRepository.RegisterApi registerApi;
    private UserRepository.LoginApi loginApi;
    private UserRepository.GetUserByUsernameApi getUserApi;
    private UserRepository.UpdateUserApi updateUserApi;

    public UserRepositoryImpl() {

    }


    @Override
    public void register(String firstName, String lastName, String username, String password, final RegistrationContract.Interactor.OnFinishedListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                registerApi = RetrofitClient
                        .getInstance()
                        .getRetrofit()
                        .create(UserRepository.RegisterApi.class);

                Call<User> call = registerApi
                        .register(firstName, lastName, username, password);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            listener.onFinished(3);
                            return;
                        }

                        User user = response.body();
                        listener.onFinished(1);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listener.onFinished(0);
                        Log.d("onFailure", t.getMessage());
                    }
                });
            }
        }, TIME_OUT);

        Log.d("Register", "finished registeration");
    }

    @Override
    public void login(String username, String password, LoginContract.LoginInteractor.OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginApi = RetrofitClient
                        .getInstance()
                        .getRetrofit()
                        .create(UserRepository.LoginApi.class);

                Call<LoginResponse> call = loginApi.login(username, password);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (!response.isSuccessful()){
                            Log.d(TAG, "Response status: Response was not Successful");
                            return;
                        }

                        LoginResponse loginResponse = response.body();

                        if (!loginResponse.isError()){
                            Log.d(TAG, "Login credentials: " + username + " " + password);
                            listener.onFinished(true, "User is Valid");
                        }else{
                            listener.onFinished(false, "User is Not Valid");
                            Log.d(TAG, String.valueOf(loginResponse.error));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("onFailure", t.getMessage());
                        listener.onFinished(false, t.getMessage());
                    }
                });
            }
        }, TIME_OUT);
    }

    @Override
    public void getUser(String username, ProfileContract.ProfileInteractor.OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getUserApi = RetrofitClient
                        .getInstance()
                        .getRetrofit()
                        .create(UserRepository.GetUserByUsernameApi.class);

                Call<User> call = getUserApi
                        .getUser(username);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            listener.onGetUserFinished(null, "Response not Success !");
                            Log.d("GetUserApi Failed", null);
                        }

                        if (response.body() != null){
                            User user = new User(response.body().getFirstName(), response.body().getLastName(), response.body().getUsername());
                            listener.onGetUserFinished(user, "Got user Success !");
                            Log.d("GetUserApi Success", user.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listener.onGetUserFinished(null, t.getMessage());

                    }
                });
            }
        }, TIME_OUT);
    }

    @Override
    public void update(String firstName, String lastName, String password, String username, ProfileContract.ProfileInteractor.OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateUserApi = RetrofitClient
                        .getInstance()
                        .getRetrofit()
                        .create(UserRepository.UpdateUserApi.class);

                Call<User> call = updateUserApi.updateUser(firstName, lastName, password, username);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            listener.onGetUserFinished(null, "Response not Success !");
                            Log.d("GetUserApi Failed", null);
                        }

                        if (response.body() != null){
                            User user = new User(response.body().getFirstName(), response.body().getLastName(), response.body().getUsername());
                            listener.onGetUserFinished(user, "Profile Updated -> User: " + username);
                            Log.d("UpdateUserApi Success", user.toString() + " User: " + username);
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        listener.onGetUserFinished(null, t.getMessage());
                    }
                });
            }
        }, TIME_OUT);
    }
}
