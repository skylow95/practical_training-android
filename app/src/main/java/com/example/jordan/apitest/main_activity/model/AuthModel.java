package com.example.jordan.apitest.main_activity.model;

/**
 * Created by Богдан on 16.02.2016.
 */
public interface AuthModel {

    void authin(String user, String pass);

    void setCallback(OnAuthFinished onAuthFinished);

}
