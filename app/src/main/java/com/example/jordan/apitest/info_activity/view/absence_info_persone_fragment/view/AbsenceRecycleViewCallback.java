package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view;

import android.widget.ProgressBar;

/**
 * Created by Богдан on 23.02.2016.
 */
public interface AbsenceRecycleViewCallback {

    void onDeleteClick(int position, ProgressBar progressBar);

    void onUpdateClick(int position, ProgressBar progressBar);
}
