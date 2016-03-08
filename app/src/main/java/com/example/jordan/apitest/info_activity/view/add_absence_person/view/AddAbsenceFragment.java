package com.example.jordan.apitest.info_activity.view.add_absence_person.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jordan.apitest.AbsenceTypeForId;
import com.example.jordan.apitest.JsonParser;
import com.example.jordan.apitest.R;
import com.example.jordan.apitest.SnackBar;
import com.example.jordan.apitest.enteties.additional_model.DateTime;
import com.example.jordan.apitest.enteties.models.Absence;
import com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view.AbsenceInfoPersonFragment;
import com.example.jordan.apitest.info_activity.view.add_absence_person.model.date_time_picker.DateTimePicker;
import com.example.jordan.apitest.info_activity.view.add_absence_person.model.date_time_picker.SimpleDateTimePicker;
import com.example.jordan.apitest.info_activity.view.add_absence_person.presenter.AddAbsencePresenter;
import com.example.jordan.apitest.info_activity.view.add_absence_person.presenter.AddAbsencePresenterImpl;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.client.Response;

/**
 * Created by Богдан on 22.02.2016.
 */
public class AddAbsenceFragment extends Fragment implements AddAbsenceView, View.OnClickListener, DateTimePicker.OnDateTimeSetListener {

    @Bind(R.id.absenceTypeIdSpinner)
    Spinner absenceTypeIdSpinner;

    @Bind(R.id.startEditText)
    EditText startEditText;

    @Bind(R.id.startDateButton)
    Button startDate;

    @Bind(R.id.endEditText)
    EditText endEditText;

    @Bind(R.id.endDateButton)
    Button endDate;

    @Bind(R.id.createAbsences)
    Button createAbsences;

    @Bind(R.id.clSnackbar)
    CoordinatorLayout coordinatorLayout;

    AddAbsencePresenter mAddAbsencePresenter;



    public static Fragment newInstance() {
        return new AddAbsenceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_absences, container, false);
        ButterKnife.bind(this,root);

        String[] items = new String[] { "For manager only", "Day off", "Vacation" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
        absenceTypeIdSpinner.setAdapter(adapter);

        createAbsences.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        mAddAbsencePresenter = new AddAbsencePresenterImpl(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        SimpleDateTimePicker simpleDateTimePicker = SimpleDateTimePicker.make(
                "Set Date & Time Title",
                new Date(),
                this,
                getFragmentManager()
        );
        switch(v.getId()){
            case R.id.createAbsences:
                try {
                    String startDate = startEditText.getText().toString();
                    String endDate = endEditText.getText().toString();
                    if(!checkEditText(startDate,endDate)){
                        onFailure();
                    } else {
                        Map<String, String> options = new HashMap<>();
                        options.put("absenceTypeId", absenceTypeIdSpinner.getSelectedItem().toString());
                        options.put("startDateTime", AbsenceTypeForId.returnDate(startDate));
                        options.put("endDateTime", AbsenceTypeForId.returnDate(endDate));
                        options.put("employeeId", Integer.toString(1));
                        mAddAbsencePresenter.addAbsence(options);

                    }
                } catch (ParseException e) {
                        e.printStackTrace();
                    }
                break;

            case R.id.startDateButton:
                simpleDateTimePicker.show(R.id.startDateButton);
                break;

            case R.id.endDateButton:
                simpleDateTimePicker.show(R.id.endDateButton);
                break;
        }
    }

    @Override
    public void DateTimeSet(Date date, int id) {
        DateTime mDateTime = new DateTime(date);

        switch (id){
            case R.id.startDateButton:
                startEditText.setText(mDateTime.getDateString());
                break;

            case R.id.endDateButton:
                endEditText.setText(mDateTime.getDateString());
                break;
        }
    }

    @Override
    public void onSuccess(Response response) {
        String result = JsonParser.convertJsonToString(response);
        Absence absence = new Gson().fromJson(result,Absence.class);
        int x = absence.getAbsenceTypeId();
        AbsenceInfoPersonFragment.absencesTypeList.add(absence);
        SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.addAbsenceString),Snackbar.LENGTH_LONG, Color.GREEN).show();
    }

    @Override
    public void onFailure() {
        SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.errors),Snackbar.LENGTH_LONG, Color.RED).show();
    }

    public boolean checkEditText(String startDateTime, String endDateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setLenient(false);
        if(startDateTime.isEmpty() && endDateTime.isEmpty()) {
            return false;
        }

        if(startDateTime.length() > 19 || endDateTime.length() > 19){
           return false;
        }
        try {
            format.parse(startDateTime);
            format.parse(endDateTime);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
