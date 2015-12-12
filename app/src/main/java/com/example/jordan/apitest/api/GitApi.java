package com.example.jordan.apitest.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @author jordan on 12.12.15.
 */
public interface GitApi {

    @FormUrlEncoded
    @POST("/index.php")
    void myPost(@Field("username") String login, Callback<Response> responseCallback);

}
