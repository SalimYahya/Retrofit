package com.example.retrofit.model.user;

import com.example.retrofit.model.user.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserRepository {

    interface RegisterApi{
        @FormUrlEncoded
        @POST("user/registerUser.php")
        Call<User> register(
                @Field("firstName") String firstName,
                @Field("lastName") String lastName,
                @Field("username") String username,
                @Field("password") String password
        );
    }

    interface LoginApi{
        @FormUrlEncoded
        @POST("user/userLogin.php")
        Call<LoginResponse> login(
                @Field("username") String username,
                @Field("password") String password
        );
    }


    interface UpdateUserApi{
        @FormUrlEncoded
        @POST("user/updateUser.php")
        Call<User> updateUser(
                @Field("firstName") String firstName,
                @Field("lastName") String lastName,
                @Field("password") String password,
                @Field("username") String username
        );
    }

    interface GetUserByUsernameApi{
        @GET("user/getUser.php")
        Call<User> getUser(@Query("username") String username);
    }
}
