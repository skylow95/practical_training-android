package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.update;

import com.example.jordan.apitest.api.RequestManager;
import com.example.jordan.apitest.api.RequestMethod;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;

import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public class UpdateAbsenceInfoModelImpl implements UpdateAbsenceInfoModel, Callback<Response> {

    OnActionFinished mOnActionFinished;

    @Override
    public void update(Map<String,String> options) {
        RequestManager.send(RequestMethod.UPDATE_ABSENCE,options,this);
    }

    @Override
    public void setCallback(OnActionFinished onActionFinished) {
        mOnActionFinished = onActionFinished;
    }

    @Override
    public void success(Response response, Response response2) {
        mOnActionFinished.onSuccess(response, RequestMethod.UPDATE_ABSENCE );
    }

    @Override
    public void failure(RetrofitError error) {
        mOnActionFinished.onFailure();
    }

}
