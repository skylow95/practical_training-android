package com.example.jordan.apitest.main_activity.presenter;

import android.text.TextUtils;

import com.example.jordan.apitest.main_activity.model.AuthModel;
import com.example.jordan.apitest.main_activity.model.AuthModelImpl;
import com.example.jordan.apitest.main_activity.model.OnAuthFinished;
import com.example.jordan.apitest.main_activity.view.MainView;

import retrofit.client.Response;

/**
 * Created by Богдан on 16.02.2016.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter, OnAuthFinished {

    private AuthModel mAuthModel;

    private MainView mMainView;

    public MainActivityPresenterImpl(MainView mainView){
        mAuthModel = new AuthModelImpl();
        mAuthModel.setCallback(this);
        mMainView = mainView;
    }

    @Override
    public void authin(String user, String pass) {
        if (!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(pass)) {
            mAuthModel.authin(user,pass);
        } else {
            mMainView.onFailure();
        }
    }

    @Override
    public void onSuccess(Response response) {
        mMainView.onSuccess(response);
    }

    @Override
    public void onFailure() {
        mMainView.onFailure();
    }
}
