package com.example.jordan.apitest.api;

import android.util.Base64;

import com.example.jordan.apitest.AbsenceTypeForId;
import com.example.jordan.apitest.Values;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * @author jordan on 13.02.16.
 */
public class RequestManager {

    public static RestApi sRestApi;

    private static RestAdapter.Builder sBuilder = new RestAdapter.Builder()
            .setEndpoint(Values.GIT_LINK)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setClient(new OkClient(new OkHttpClient()))
            .setConverter(new GsonConverter(new GsonBuilder()
                                            .setFieldNamingPolicy(
                                                    FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create())
            );


    public static void build(final String basic){
        sBuilder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Authorization", basic);
                request.addHeader("Accept", "application/json");
            }
        });
        RestAdapter restAdapter = sBuilder.build();
        sRestApi = restAdapter.create(RestApi.class);
    }

    public static String toBasic64(String userName, String userPass){
        String credentials = userName + ":" + userPass;
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(),
                              Base64.NO_WRAP);
        return basic;
    }


    public static void send(RequestMethod requestMethod, Map<String, String> options,
                            Callback<Response> cb) {
        switch (requestMethod) {
            case GET_PERSON_INFO:
                sRestApi.getAbsencesPerson(cb);
                break;
            case ADD_NEW_ABSENCE:
                Integer absenceTypeId = AbsenceTypeForId.returnAbsenceTypeId(
                        options.get("absenceTypeId"));
                sRestApi.addAbsences(absenceTypeId,
                                    options.get("startDateTime"),
                                    options.get("endDateTime"),
                                    Integer.parseInt(options.get("employeeId")),
                                    cb);
                break;
            case DELETE_ABSENCE:
                sRestApi.deleteAbsences(Integer.parseInt(options.get("absenceId")),cb);
                break;
            case UPDATE_ABSENCE:
                int statusId =  AbsenceTypeForId.returnStatusId(options.get("statusId"));
                sRestApi.updateAbsences(Integer.parseInt(options.get("absenceTypeId")),
                                        "",
                                        statusId,
                                        cb);
                break;
            default: break;
        }
    }

}
