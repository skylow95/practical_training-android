package com.example.jordan.apitest.main_activity.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jordan.apitest.JsonParser;
import com.example.jordan.apitest.R;
import com.example.jordan.apitest.SnackBar;
import com.example.jordan.apitest.enteties.models.AbsencesType;
import com.example.jordan.apitest.info_activity.view.InfoActivity;
import com.example.jordan.apitest.main_activity.presenter.MainActivityPresenter;
import com.example.jordan.apitest.main_activity.presenter.MainActivityPresenterImpl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.client.Response;


public class MainActivity extends Activity implements View.OnClickListener,MainView {

    @Bind(R.id.buttonConfirm)
    Button buttonConfirm;

    @Bind(R.id.userName)
    EditText userName;

    @Bind(R.id.userPass)
    EditText userPass;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private MainActivityPresenter mMainActivityPresenter;

    public static List<AbsencesType> sAbsencesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        mMainActivityPresenter = new MainActivityPresenterImpl(this);

        buttonConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user_Name = userName.getText().toString();
        String user_Pass = userPass.getText().toString();

        mMainActivityPresenter.authin(user_Name, user_Pass);
    }

    @Override
    public void onSuccess(Response response) {
        String textJson = JsonParser.convertJsonToString(response);
      //  String x = textJson.substring(9,textJson.length() - 1);
        sAbsencesList = JsonParser.toList(textJson,AbsencesType.class);
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        SnackBar.makeSnackbar(coordinatorLayout,getString(R.string.error_login),Snackbar.LENGTH_LONG, Color.RED).show();
    }
}
