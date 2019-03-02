package com.jss.abhi.zealicon.utils;

import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.model.InnerData;

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

    public static String get(JSONObject jsonObject,String key){
        if(jsonObject.has(key)){
            try {
                String string=jsonObject.getString(key);
                return string;
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        }
        else{
            return "";
        }
    }

    public static EventData toObject(String jsonString){
        EventData eventData=new
                EventData();
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
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
            eventData.setName(get(jsonObject,"name"));
            eventData.setCategory_id(get(jsonObject, "category_id"));
            eventData.setDescription(get(jsonObject,"description"));
            eventData.setContact_name(get(jsonObject, "contact_name"));
            eventData.setContact_no(get(jsonObject, "contact_no"));
            eventData.setWinner1(get(jsonObject, "winner1"));
            eventData.setWinner2(get(jsonObject,  "winner2"));
            eventData.setId(get(jsonObject, "id"));
            //innerData.setLong_des(get(jsonObject,"long_des"));
            /*String prizes=get(jsonObject,"prize_money");
            JSONArray prizeList=new JSONArray(prizes);
            if(prizeList.length()>=1)
                innerData.setPrize1(prizeList.getString(0));
            if (prizeList.length()==2)
                innerData.setPrize2(prizeList.getString(1));
            innerData.setRules(get(jsonObject,"rules"));
            String contactStr=get(jsonObject,"contact");
            JSONArray contacts=new JSONArray(contactStr);
            try {
                JSONObject contact1obj = contacts.getJSONObject(0);
                innerData.setContact_name1(get(contact1obj,"name"));
                innerData.setContact_num1(get(contact1obj,"number"));
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                JSONObject contact2obj = contacts.getJSONObject(1);
                innerData.setContact_name2(get(contact2obj,"name"));
                innerData.setContact_num2(get(contact2obj,"number"));
            }catch (Exception e){
                e.printStackTrace();
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventData;
    }
}
