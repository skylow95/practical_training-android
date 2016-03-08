package com.example.jordan.apitest.main_activity.model;

import retrofit.client.Response;

/**
 * Created by Богдан on 16.02.2016.
 */
public interface OnAuthFinished {

    void onSuccess(Response response);

    void onFailure();
}
