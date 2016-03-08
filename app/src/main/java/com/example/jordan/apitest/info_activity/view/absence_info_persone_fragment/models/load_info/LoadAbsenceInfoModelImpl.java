package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.load_info;

import com.example.jordan.apitest.api.RequestManager;
import com.example.jordan.apitest.api.RequestMethod;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public class LoadAbsenceInfoModelImpl implements LoadAbsenceInfoModule, Callback<Response> {

    OnActionFinished mOnActionFinished;

    @Override
    public void load() {
        RequestManager.send(RequestMethod.GET_PERSON_INFO,null,this);
    }

    @Override
    public void setCallback(OnActionFinished onLoadFinished) {
        mOnActionFinished = onLoadFinished;
    }

    @Override
    public void success(Response response, Response response2) {
        mOnActionFinished.onSuccess(response, RequestMethod.GET_PERSON_INFO);
    }

    @Override
    public void failure(RetrofitError error) {
        mOnActionFinished.onFailure();
    }
}
