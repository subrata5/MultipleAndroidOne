package com.example.subrata.firstapplication.MvpPatternValidation.Model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class User implements IUser {

    private String email, password;

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;

    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isValid() {

        //check if the email is empty
        //check if the email matches the pattern
        //check for password length to be >6


        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
                getPassword().length() > 6;

    }
}
