package com.example.smtrick.smartnanded.repository;

import com.example.smtrick.smartnanded.callback.CallBack;

public interface UserRepository {

    void signIn(final String mobileNumber, final String password, final CallBack callback);

    void readUserByUserId(final String regId, final CallBack callBack);
}