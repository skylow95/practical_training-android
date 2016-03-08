package com.example.jordan.apitest.enteties.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("language")
    @Expose
    private String language;

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValueFromLanguage(){
        return value;
    }
}