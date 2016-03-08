package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.presenter;

import java.util.Map;

/**
 * Created by Богдан on 24.02.2016.
 */
public interface AbsenceInfoPersonPresenter {

    void delete(Map<String,String> options);

    void update(Map<String,String> options);

    void load();
}
