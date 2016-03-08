package com.example.jordan.apitest.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author jordan on 12.12.15.
 */
public interface RestApi {

    @GET("/v1/absences")
    void getAbsencesPerson (Callback<Response> responseCallback);

    @GET("/v1/types")
    void login(Callback<Response> callback);

    @POST("/v1/absences")
    @FormUrlEncoded
    void addAbsences(@Field("absenceTypeId")Integer absenceTypeId ,
                     @Field("startDateTime")String startDateTime ,
                     @Field("endDateTime") String endDateTime,
                     @Field("employeeId") int id,
                     Callback<Response> responseCallback);

    @DELETE("/v1/absences/{id}")
    void deleteAbsences(@Path("id") int absenceId, Callback<Response> responseCallback);

    @PUT("/v1/absences/{id}")
    void updateAbsences(@Path("id") int absenceId, @Query("statusId") String emptyString, @Body int statusId, Callback<Response> responseCallback);
}
