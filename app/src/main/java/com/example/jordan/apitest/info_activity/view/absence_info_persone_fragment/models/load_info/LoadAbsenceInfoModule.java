package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.load_info;

import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface LoadAbsenceInfoModule {

    void load();

    void setCallback(OnActionFinished onActionFinished);
}
