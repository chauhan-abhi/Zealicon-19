package com.jss.abhi.zealicon.utils;

import com.jss.abhi.zealicon.model.EventData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mrsinghania on 9/3/18.
 */

public class Jsonparser {

    public static ArrayList<EventData> stringToEventArray(String dayArrayString) {
        ArrayList<EventData> eventDataList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(dayArrayString);
            for (int j = 0; j < jsonArray.length(); j++) {
                EventData eventData = new EventData();
                try {
                    eventData = Jsonparser.toObject(jsonArray.getJSONObject(j).toString());
                    eventDataList.add(eventData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventDataList;
    }

    public static String get(JSONObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            try {
                String string = jsonObject.getString(key);
                return string;
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static EventData toObject(String jsonString) {
        EventData eventData = new
                EventData();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            /*innerData.setTimings(get(jsonObject,"timing"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date(); // You will need try/catch around this
            try {
                date = formatter.parse(innerData.getTimings());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            innerData.setEvent_date(calendar.get(Calendar.DATE));
            innerData.setEvent_time(calendar.get(Calendar.HOUR_OF_DAY));*/
            eventData.setId(get(jsonObject, "__v"));
            eventData.setName(get(jsonObject, "name"));
            //eventData.setCategory_id(get(jsonObject, "category_id"));
            String desc = get(jsonObject, "description").equals("Write your content here")?"Desciption not updated":get(jsonObject, "description");
            eventData.setDescription(desc);
            //eventData.setContact_name(get(jsonObject, "contact_name"));
            //eventData.setContact_no(get(jsonObject, "contact_no"));
            //eventData.setWinner1(get(jsonObject, "winner1"));
            //eventData.setWinner2(get(jsonObject,  "winner2"));
            eventData.setSocietyId(get(jsonObject, "creatorname"));

            /**
             * it has to be replaced
             */
            //eventData.setTiming(get(jsonObject, "timing"));
            String s[] = parseTime(get(jsonObject, "date"));
            eventData.setTiming(s[0]);
            eventData.setFullDate(s[1]);
            eventData.setVenue(get(jsonObject, "venue"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventData;
    }

    private static String[] parseTime(String date) {
        String result[] = new String[2];
        date = date.substring(4, 21);
        SimpleDateFormat format24  = new SimpleDateFormat("MMM dd yyyy hh:mm");
        SimpleDateFormat format12  = new SimpleDateFormat("hh:mm a");

        Date d=  new Date();
        try {
            d= format24.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            result[0] = format12.format(d).replace("am","AM").replace("pm","PM");
            result[1] = date.substring(0, 11);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
