package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.presenter;

import com.example.jordan.apitest.api.RequestMethod;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.OnActionFinished;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.delete.DeleteAbsenceModel;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.delete.DeleteAbsenceModelImpl;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.load_info.LoadAbsenceInfoModelImpl;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.load_info.LoadAbsenceInfoModule;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.update.UpdateAbsenceInfoModel;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.models.update.UpdateAbsenceInfoModelImpl;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view.AbsencePersonFragmentView;

import java.util.Map;

import retrofit.client.Response;

/**
 * Created by Богдан on 24.02.2016.
 */
public class AbsenceInfoPersonPresenterImpl implements AbsenceInfoPersonPresenter , OnActionFinished {

    LoadAbsenceInfoModule mLoadAbsenceInfo;

    UpdateAbsenceInfoModel mUpdateAbsenceInfo;

    DeleteAbsenceModel mDeleteAbsence;

    AbsencePersonFragmentView mAbsencePersonFragmentView;

    public AbsenceInfoPersonPresenterImpl(AbsencePersonFragmentView personFragmentView){
        mDeleteAbsence = new DeleteAbsenceModelImpl();
        mDeleteAbsence.setCallback(this);
        mLoadAbsenceInfo = new LoadAbsenceInfoModelImpl();
        mLoadAbsenceInfo.setCallback(this);
        mUpdateAbsenceInfo = new UpdateAbsenceInfoModelImpl();
        mUpdateAbsenceInfo.setCallback(this);
        mAbsencePersonFragmentView = personFragmentView;
    }


    @Override
    public void delete(Map<String,String> options) {
        mDeleteAbsence.delete(options);
    }

    @Override
    public void update(Map<String,String> options) {
        mUpdateAbsenceInfo.update(options);
    }

    @Override
    public void load() {
        mLoadAbsenceInfo.load();
    }

    @Override
    public void onSuccess(Response response, RequestMethod requestMethod) {
        mAbsencePersonFragmentView.onSuccess(response,requestMethod );
    }

    @Override
    public void onFailure() {
        mAbsencePersonFragmentView.onFailure();
    }
}
