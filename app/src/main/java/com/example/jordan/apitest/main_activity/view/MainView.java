package com.example.jordan.apitest.main_activity.view;

import retrofit.client.Response;

/**
 * Created by Богдан on 16.02.2016.
 */
public interface MainView {

    void onSuccess(Response response);

    void onFailure();
}
