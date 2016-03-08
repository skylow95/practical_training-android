package com.example.jordan.apitest;

import com.example.jordan.apitest.enteties.additional_model.ListOfJson;
import com.example.jordan.apitest.enteties.models.Absence;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.client.Response;

/**
 * Created by Богдан on 19.02.2016.
 */
public class JsonParser {



    public static String convertJsonToString(Response response){
        //Try to get response body
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static <T> List<T> toList(String json, Class<T> typeClass)
    {
        Gson mGson = new Gson();
        return mGson.fromJson(json, new ListOfJson<T>(typeClass));
    }

    public static int checkResponseMessage(String json){
        if(json.contains("shortName") && json.contains("externalReference")){
            return 1;
        } else {
            if(json.contains("startDateTime") && json.contains("endDateTime"))
            {
                return 2;
            }
        }
        return 0;
    }

    public static String toResultForEmployee(List<Absence> list){
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i).getStatusId()
                    + "\nStart date:" + list.get(i).getStartDateTime()
                    + "\nEnd date:" + list.get(i).getEndDateTime() + "\n\n";
        }
        return result;
    }


}
