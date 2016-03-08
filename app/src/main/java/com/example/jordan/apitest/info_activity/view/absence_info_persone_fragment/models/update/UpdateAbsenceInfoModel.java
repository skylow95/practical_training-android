package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.update;

import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;

import java.util.Map;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface UpdateAbsenceInfoModel {

    void update(Map<String,String> options);

    void setCallback(OnActionFinished onActionFinished);
}
