package com.example.jordan.apitest.enteties.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employee implements Serializable{


    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("userpass")
    @Expose
    private String userPass;

    public Employee(String userName, String userPass){
        this.userName = userName;
        this.userPass = userPass;
    }

    /**
     *
     * @return
     * The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * The username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The userpass
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     *
     * @param userPass
     * The userPass
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}