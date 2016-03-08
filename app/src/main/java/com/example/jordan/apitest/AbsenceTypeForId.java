package com.example.jordan.apitest;

import com.example.jordan.apitest.enteties.models.AbsencesType;
import com.example.jordan.apitest.main_activity.view.MainActivity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Богдан on 19.02.2016.
 */
public class AbsenceTypeForId {

    public static String readAbsenceTypeId(int id) {
        List<AbsencesType> list = MainActivity.sAbsencesList;
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                return list.get(i).getTraslationName().get("ENGLISH");
            }
        }
        return null;
    }

    public static String readStatusId(int id){
        if(id == 1){
            return "Waiting";
        }
        if(id == 2){
            return "Confirmed";
        }
        if(id == 3){
            return "Declined";
        }
        if(id == 4) {
            return "Status id #4";
        }
        if(id == 5){
            return "Status id #5";
        }
        return null;
    }

    public static int returnStatusId(String status){
        if(status.equals("Waiting")){
            return 1;
        }
        if(status.equals("Confirmed")){
            return 2;
        }
        if(status.equals("Declined")){
            return 3;
        }
        return 0;
    }

    public static Integer returnAbsenceTypeId(String type){
        List<AbsencesType> list = MainActivity.sAbsencesList;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTraslationName().get("ENGLISH").equals(type)){
                return list.get(i).getId();
            }

        }
        return null;
    }

    public static String returnDate(String dateTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Date currentDate = format.parse(dateTime);
        DateTime time = new DateTime(currentDate);
        DateTimeZone dtZone = DateTimeZone.forID("America/Anchorage");
        DateTime dt = time.withZone(dtZone);
        String x = dt.toString("yyyy-MM-dd'T'HH:mmZ");
        x = x.substring(0,19) + ":" + x.substring(19,21);
        String xz = x + "[" + dtZone + "]";
        return xz;

    }
}
