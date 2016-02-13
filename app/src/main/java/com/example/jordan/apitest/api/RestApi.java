package com.example.jordan.apitest.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * @author jordan on 12.12.15.
 */
public interface RestApi {

    @GET("/users/{username}")
    void getTest(@Path("username") String userName, Callback<Response> responseCallback);

}
