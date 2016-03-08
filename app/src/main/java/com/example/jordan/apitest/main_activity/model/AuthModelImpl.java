package com.example.jordan.apitest.main_activity.model;

import com.example.jordan.apitest.api.RequestManager;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Богдан on 16.02.2016.
 */
public class AuthModelImpl implements AuthModel, Callback<Response> {

    private OnAuthFinished mOnAuthFinished;

    @Override
    public void authin(String user, String pass) {

        final String basic = RequestManager.toBasic64(user, pass);
        RequestManager.build(basic);
        RequestManager.sRestApi.login(this);
    }

    @Override
    public void setCallback(OnAuthFinished onAuthFinished) {
        mOnAuthFinished = onAuthFinished;
    }

    @Override
    public void success(Response response, Response response2) {
        mOnAuthFinished.onSuccess(response);
    }

    @Override
    public void failure(RetrofitError error) {
        mOnAuthFinished.onFailure();
    }
}
