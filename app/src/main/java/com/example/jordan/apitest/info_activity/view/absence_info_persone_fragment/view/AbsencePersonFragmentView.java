package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view;

import com.example.jordan.apitest.api.RequestMethod;

import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface AbsencePersonFragmentView {

    void onSuccess(Response response, RequestMethod requestMethod);

    void onFailure();
}
