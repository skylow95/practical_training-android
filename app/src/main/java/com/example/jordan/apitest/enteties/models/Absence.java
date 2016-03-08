package com.example.jordan.apitest.enteties.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Absence {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("absenceTypeExternalReference")
    @Expose
    private Object absenceTypeExternalReference;
    @SerializedName("absenceTypeId")
    @Expose
    private Integer absenceTypeId;
    @SerializedName("employeeExternalReference")
    @Expose
    private Object employeeExternalReference;
    @SerializedName("employeeId")
    @Expose
    private Integer employeeId;

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
     * The statusId
     */
    public Integer getStatusId() {
        return statusId;
    }

    /**
     *
     * @param statusId
     * The statusId
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     *
     * @return
     * The startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     *
     * @param startDateTime
     * The startDateTime
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     *
     * @return
     * The endDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     *
     * @param endDateTime
     * The endDateTime
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     *
     * @return
     * The absenceTypeExternalReference
     */
    public Object getAbsenceTypeExternalReference() {
        return absenceTypeExternalReference;
    }

    /**
     *
     * @param absenceTypeExternalReference
     * The absenceTypeExternalReference
     */
    public void setAbsenceTypeExternalReference(Object absenceTypeExternalReference) {
        this.absenceTypeExternalReference = absenceTypeExternalReference;
    }

    /**
     *
     * @return
     * The absenceTypeId
     */
    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    /**
     *
     * @param absenceTypeId
     * The absenceTypeId
     */
    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
    }

    /**
     *
     * @return
     * The employeeExternalReference
     */
    public Object getEmployeeExternalReference() {
        return employeeExternalReference;
    }

    /**
     *
     * @param employeeExternalReference
     * The employeeExternalReference
     */
    public void setEmployeeExternalReference(Object employeeExternalReference) {
        this.employeeExternalReference = employeeExternalReference;
    }

    /**
     *
     * @return
     * The employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId
     * The employeeId
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    public Absence(Integer absenceTypeId, String startDateTime, String endDateTime, Integer employeeId){
        this.absenceTypeId = absenceTypeId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.employeeId = employeeId;
        this.employeeExternalReference = null;
        this.absenceTypeExternalReference = null;
        this.statusId = 1;
      //  this.id = absenceTypeId + (int)Math.random() * employeeId;
    }

}