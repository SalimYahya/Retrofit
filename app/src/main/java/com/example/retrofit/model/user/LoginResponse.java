package com.example.retrofit.model.user;

public class LoginResponse {

    boolean error;

    public LoginResponse(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
