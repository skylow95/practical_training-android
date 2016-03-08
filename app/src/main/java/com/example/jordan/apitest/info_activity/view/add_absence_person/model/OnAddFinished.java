package com.example.jordan.apitest.info_activity.view.add_absence_person.model;

import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface OnAddFinished {

    void onSuccess(Response response);

    void onFailure();
}
