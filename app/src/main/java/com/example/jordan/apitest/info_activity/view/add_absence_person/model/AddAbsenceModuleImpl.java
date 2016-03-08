package com.example.jordan.apitest.info_activity.view.add_absence_person.model;

import com.example.jordan.apitest.api.RequestManager;
import com.example.jordan.apitest.api.RequestMethod;

import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public class AddAbsenceModuleImpl implements AddAbsenseModule, Callback<Response>{

    OnAddFinished mOnAddFinished;

    @Override
    public void addAbsence(Map<String, String> options) {
        RequestManager.send(RequestMethod.ADD_NEW_ABSENCE,options,this);
    }

    @Override
    public void setCallback(OnAddFinished callback) {
        mOnAddFinished = callback;
    }


    @Override
    public void success(Response response, Response response2) {
        mOnAddFinished.onSuccess(response);
    }

    @Override
    public void failure(RetrofitError error) {
        mOnAddFinished.onFailure();
    }
}
