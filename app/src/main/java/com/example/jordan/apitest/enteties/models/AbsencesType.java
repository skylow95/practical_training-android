package com.example.jordan.apitest.enteties.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class AbsencesType {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("externalReference")
    @Expose
    private Object externalReference;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("traslationName")
    @Expose
    private HashMap<String,String> traslationName;

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
     * The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     * The shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     * The externalReference
     */
    public Object getExternalReference() {
        return externalReference;
    }

    /**
     *
     * @param externalReference
     * The externalReference
     */
    public void setExternalReference(Object externalReference) {
        this.externalReference = externalReference;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The traslationName
     */
    public HashMap<String, String> getTraslationName() {
        return traslationName;
    }

    /**
     *
     * @param traslationName
     * The traslationName
     */
    public void setTraslationName(HashMap<String,String> traslationName) {
        this.traslationName = traslationName;
    }

}


