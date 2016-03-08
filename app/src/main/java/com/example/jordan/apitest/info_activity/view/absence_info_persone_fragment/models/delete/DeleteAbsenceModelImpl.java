package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.delete;

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
public class DeleteAbsenceModelImpl implements DeleteAbsenceModel, Callback<Response> {

    OnActionFinished mActionFinished;

    @Override
    public void delete(Map<String,String> options) {
        RequestManager.send(RequestMethod.DELETE_ABSENCE, options, this);
    }


    @Override
    public void setCallback(OnActionFinished onDeleteFinished) {
        mActionFinished = onDeleteFinished;
    }

    @Override
    public void success(Response response, Response response2) {
        mActionFinished.onSuccess(response, RequestMethod.DELETE_ABSENCE );
    }

    @Override
    public void failure(RetrofitError error) {
        mActionFinished.onFailure();
    }


}
