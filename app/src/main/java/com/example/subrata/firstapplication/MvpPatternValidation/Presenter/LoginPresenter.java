package com.example.subrata.firstapplication.MvpPatternValidation.Presenter;

import com.example.subrata.firstapplication.MvpPatternValidation.Model.User;
import com.example.subrata.firstapplication.MvpPatternValidation.View.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView)
    {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {

        User user = new User(email, password);
        boolean isLoginSuccess = user.isValid();

        if(isLoginSuccess)
            loginView.onLoginResult("Login Success");
        else
            loginView.onLoginResult("Login error!");

    }
}
