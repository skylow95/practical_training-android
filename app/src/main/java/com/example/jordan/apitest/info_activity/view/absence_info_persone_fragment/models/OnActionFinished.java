package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models;

import com.example.jordan.apitest.api.RequestMethod;

import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface OnActionFinished {

    void onSuccess(Response response, RequestMethod requestMethod);

    void onFailure();
}
