package com.example.jordan.apitest.api;

import com.example.jordan.apitest.Values;

import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * @author jordan on 13.02.16.
 */
public class RequestManager {

    RestApi git;

    public RequestManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Values.GIT_LINK).setLogLevel(RestAdapter.LogLevel.FULL).build();

        git = restAdapter.create(RestApi.class);
    }

    public void send(RequestMethod requestMethod, Map<String, String> options,
                     Callback<Response> cb) {
        switch (requestMethod) {
            case GET_USER_INFO:
                git.getTest(options.get("username"), cb);
                break;
        }
    }

}
