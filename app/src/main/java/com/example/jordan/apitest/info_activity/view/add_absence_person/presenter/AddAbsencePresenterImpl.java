package com.example.jordan.apitest.info_activity.view.add_absence_person.presenter;

import com.example.jordan.apitest.info_activity.view.add_absence_person.model.AddAbsenceModuleImpl;
import com.example.jordan.apitest.info_activity.view.add_absence_person.model.AddAbsenseModule;
import com.example.jordan.apitest.info_activity.view.add_absence_person.model.OnAddFinished;
import com.example.jordan.apitest.info_activity.view.add_absence_person.view.AddAbsenceView;

import java.util.Map;

import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public class AddAbsencePresenterImpl implements AddAbsencePresenter, OnAddFinished{

    AddAbsenceView mAddAbsenceView;

    AddAbsenseModule mAddAbsenceModule;

    public AddAbsencePresenterImpl(AddAbsenceView view){
        mAddAbsenceModule = new AddAbsenceModuleImpl();
        mAddAbsenceModule.setCallback(this);
        mAddAbsenceView = view;
    }


    @Override
    public void addAbsence(Map<String, String> options) {
        if(!options.get("startDateTime").isEmpty() && !options.get("endDateTime").isEmpty()) {
            mAddAbsenceModule.addAbsence(options);
        } else {
           mAddAbsenceView.onFailure();
        }
    }

    @Override
    public void onSuccess(Response response) {
        mAddAbsenceView.onSuccess(response);
    }

    @Override
    public void onFailure() {
        mAddAbsenceView.onFailure();
    }
}
