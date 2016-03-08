package com.example.jordan.apitest;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Богдан on 26.02.2016.
 */
public class SnackBar
{
    public static Snackbar makeSnackbar(@NonNull View layout, @NonNull CharSequence  text, int duration, int textColor){
        Snackbar snackBarView = Snackbar.make(layout, text, duration);
        TextView tv = (TextView) snackBarView.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(textColor);
        return snackBarView;
    }
}
