package com.example.jordan.apitest.info_activity.view.add_absence_person.model;

import java.util.Map;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface AddAbsenseModule {

    void addAbsence(Map<String, String> options);

    void setCallback(OnAddFinished callback);
}
