package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jordan.apitest.JsonParser;
import com.example.jordan.apitest.R;
import com.example.jordan.apitest.SnackBar;
import com.example.jordan.apitest.api.RequestMethod;
import com.example.jordan.apitest.enteties.models.Absence;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.presenter.AbsenceInfoPersonPresenter;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.presenter.AbsenceInfoPersonPresenterImpl;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.client.Response;



/**
 * Created by Богдан on 19.02.2016.
 */
public class AbsenceInfoPersonFragment extends Fragment implements AbsencePersonFragmentView,AbsenceRecycleViewCallback {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.clSnackbar)
    CoordinatorLayout coordinatorLayout;

    AbsenceInfoPersonAdapter mRecyclerViewAdapter;
    public static List<Absence> absencesTypeList;

    AbsenceInfoPersonPresenter mAbsenceInfoPersonPresenter;

    public static Fragment newInstance() {
        return new AbsenceInfoPersonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.absence_info,container,false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this,root);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mAbsenceInfoPersonPresenter = new AbsenceInfoPersonPresenterImpl(this);
        send();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void send(){
        mAbsenceInfoPersonPresenter.load();
    }

    public void delete(Map<String,String> options){
        mAbsenceInfoPersonPresenter.delete(options);
    }

    public void update(Map<String, String> options){
        mAbsenceInfoPersonPresenter.update(options);
    }


    @Override
    public void onDeleteClick(int position, ProgressBar progressBar) {
        Map<String, String> options = new HashMap<>();
        progressBar.setVisibility(View.VISIBLE);
        options.put("absenceId",Integer.toString(absencesTypeList.get(position).getId()));
        delete(options);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateClick(final int position, final ProgressBar progressBar) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title
        alertDialogBuilder.setTitle("Your Title");

        // set dialog message
        alertDialogBuilder
                .setMessage("Confirmed!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Map<String,String> options = new HashMap<>();
                        options.put("absenceTypeId",Integer.toString(absencesTypeList.get(position).getId()));
                        options.put("statusId","Confirmed");
                        progressBar.setVisibility(View.VISIBLE);
                        update(options);
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        Map<String,String> options = new HashMap<>();
                        options.put("absenceTypeId",Integer.toString(absencesTypeList.get(position).getId()));
                        options.put("statusId","Declined");
                        progressBar.setVisibility(View.VISIBLE);
                        update(options);
                        progressBar.setVisibility(View.GONE);
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void updateList(List<Absence> list){
        absencesTypeList = list;
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess(Response response, RequestMethod requestMethod) {
        switch (requestMethod){
            case GET_PERSON_INFO:
                String result = JsonParser.convertJsonToString(response);
            //    String result_READY = result.substring(53,result.length() - 1);
                absencesTypeList = JsonParser.toList(result, Absence.class);
                mRecyclerViewAdapter = new AbsenceInfoPersonAdapter(absencesTypeList, this);
                recyclerView.setAdapter(mRecyclerViewAdapter);
                break;
            case DELETE_ABSENCE:
                int id = new Gson().fromJson(JsonParser.convertJsonToString(response),int.class);
                for(int i = 0;i < absencesTypeList.size();i++){
                    if(absencesTypeList.get(i).getId() == id){
                        absencesTypeList.remove(i);
                        break;
                    }
                }
                updateList(absencesTypeList);
                SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.deleteSucess),Snackbar.LENGTH_LONG, Color.GREEN).show();
                break;
            case UPDATE_ABSENCE:
                String result_json = JsonParser.convertJsonToString(response);
                Log.d("Update", result_json);
                Absence absence = new Gson().fromJson(result_json,Absence.class);
                for(int i = 0;i < absencesTypeList.size();i++){
                    if(absence.getId() == (int)absencesTypeList.get(i).getId()){
                        absencesTypeList.get(i).setStatusId(absence.getStatusId());
                        break;
                    }
                }
                SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.updateSucess),Snackbar.LENGTH_LONG, Color.GREEN).show();
                updateList(absencesTypeList);
                break;
        }

    }

    @Override
    public void onFailure() {
        SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.errors),Snackbar.LENGTH_LONG, Color.RED).show();
    }
}

