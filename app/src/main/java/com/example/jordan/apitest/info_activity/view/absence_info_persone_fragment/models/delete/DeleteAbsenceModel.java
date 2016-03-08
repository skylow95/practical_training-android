package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.delete;

import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;

import java.util.Map;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface DeleteAbsenceModel {

    void delete(Map<String,String> options);

    void setCallback(OnActionFinished onActionFinished);
}
