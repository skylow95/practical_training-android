package com.example.jordan.apitest.info_activity.view.add_absence_person.model.date_time_picker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TimePicker;

import com.example.jordan.apitest.R;
import com.example.jordan.apitest.enteties.additional_model.DateTime;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Богдан on 24.02.2016.
 */
public class DateTimePicker extends DialogFragment {
    public static final String TAG_FRAG_DATE_TIME = "fragDateTime";
    private static final String KEY_DIALOG_TITLE = "dialogTitle";
    private static final String KEY_INIT_DATE = "initDate";
    private static final String TAG_DATE = "date";
    private static final String TAG_TIME = "time";
    private static final String KEY_ID_BUTTON = "button";

    private int mIdResourse;

    @Bind(R.id.date_picker)
    DatePicker mDatePicker;

    @Bind(R.id.time_picker)
    TimePicker mTimePicker;

    @Bind(R.id.tab_host)
    TabHost TabHost;

    private Context mContext;
    private ButtonClickListener mButtonClickListener;
    private OnDateTimeSetListener mOnDateTimeSetListener;
    private Bundle mArgument;

    // DialogFragment constructor must be empty
    public DateTimePicker() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mButtonClickListener = new ButtonClickListener();
    }
    /**
     *
     * @param dialogTitle Title of the DateTimePicker DialogFragment
     * @param initDate Initial Date and Time set to the Date and Time Picker
     * @return Instance of the DateTimePicker DialogFragment
     */
    public static DateTimePicker newInstance(CharSequence dialogTitle, Date initDate,int idResourse) {
        // Create a new instance of DateTimePicker
        DateTimePicker mDateTimePicker = new DateTimePicker();
        // Setup the constructor parameters as arguments
        Bundle mBundle = new Bundle();
        mBundle.putCharSequence(KEY_DIALOG_TITLE, dialogTitle);
        mBundle.putSerializable(KEY_INIT_DATE, initDate);
        mBundle.putInt(KEY_ID_BUTTON,idResourse);
        mDateTimePicker.setArguments(mBundle);
        // Return instance with arguments
        return mDateTimePicker;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Retrieve Argument passed to the constructor
        mArgument = getArguments();
        // Use an AlertDialog Builder to initially create the Dialog
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        // Setup the Dialog
        mBuilder.setTitle(mArgument.getCharSequence(KEY_DIALOG_TITLE));
        mBuilder.setNegativeButton(android.R.string.no,mButtonClickListener);
        mBuilder.setPositiveButton(android.R.string.yes,mButtonClickListener);
        // Create the Alert Dialog
        AlertDialog mDialog = mBuilder.create();
        // Set the View to the Dialog
        mDialog.setView(
                createDateTimeView(mDialog.getLayoutInflater())
        );
        // Return the Dialog created
        return mDialog;
    }
    /**
     * Inflates the XML Layout and setups the tabs
     * @param layoutInflater Layout inflater from the Dialog
     * @return Returns a view that will be set to the Dialog
     */
    private View createDateTimeView(LayoutInflater layoutInflater) {
        // Inflate the XML Layout using the inflater from the created Dialog
        View mView = layoutInflater.inflate(R.layout.date_choose,null);
        ButterKnife.bind(this,mView);

        mIdResourse = mArgument.getInt(KEY_ID_BUTTON);
        // Extract the TabHost
        //TabHost = (TabHost) mView.findViewById(R.id.tab_host);
        TabHost.setup();
        // Create Date Tab and add to TabHost
        TabHost.TabSpec mDateTab = TabHost.newTabSpec(TAG_DATE);
        mDateTab.setIndicator(getString(R.string.tab_date));
        mDateTab.setContent(R.id.date_content);
        TabHost.addTab(mDateTab);
        // Create Time Tab and add to TabHost
        TabHost.TabSpec mTimeTab = TabHost.newTabSpec(TAG_TIME);
        mTimeTab.setIndicator(getString(R.string.tab_time));
        mTimeTab.setContent(R.id.time_content);
        TabHost.addTab(mTimeTab);
        // Retrieve Date from Arguments sent to the Dialog
        DateTime mDateTime = new DateTime((Date) mArgument.getSerializable(KEY_INIT_DATE));
        // Initialize Date and Time Pickers
        mDatePicker.init(mDateTime.getYear(), mDateTime.getMonthOfYear(),
                mDateTime.getDayOfMonth(), null);
        mTimePicker.setCurrentHour(mDateTime.getHourOfDay());
        mTimePicker.setCurrentMinute(mDateTime.getMinuteOfHour());
        // Return created view
        return mView;
    }
    /**
     * Sets the OnDateTimeSetListener interface
     * @param onDateTimeSetListener Interface that is used to send the Date and Time
     *               to the calling object
     */
    public void setOnDateTimeSetListener(OnDateTimeSetListener onDateTimeSetListener) {
        mOnDateTimeSetListener = onDateTimeSetListener;
    }
    private class ButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int result) {
            // Determine if the user selected Ok
            if(DialogInterface.BUTTON_POSITIVE == result) {
                DateTime mDateTime = new DateTime(
                        mDatePicker.getYear(),
                        mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth(),
                        mTimePicker.getCurrentHour(),
                        mTimePicker.getCurrentMinute()
                );
                mOnDateTimeSetListener.DateTimeSet(mDateTime.getDate(),mIdResourse);
            }
        }
    }
    /**
     * Interface for sending the Date and Time to the calling object
     */
    public interface OnDateTimeSetListener {
        void DateTimeSet(Date date,int id);
    }
}
