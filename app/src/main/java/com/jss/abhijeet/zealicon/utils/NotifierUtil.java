package com.jss.abhijeet.zealicon.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.jss.abhijeet.zealicon.service.NotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

public class NotifierUtil {

    public static void notifyme(Context context, String timing, String event_name) {
        //String toParse = innerData.getTimings(); // Results in "2-5-2012 20:43"
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        Date date = new Date(); // You will need try/catch around this
        try {
            date = formatter.parse(timing);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SharedPreferences sf = context.getSharedPreferences("notify", 0);
        int notificationid = sf.getInt("key", 1);
        sf.edit().putInt(event_name, 1).apply();
        long millis = date.getTime();
        Intent intent = new Intent(context, NotificationService.AlarmReceiver.class);
        intent.putExtra("keynotify", notificationid);
        intent.putExtra("eventname", event_name);
        Log.v("keynotify1", notificationid + "");

        PendingIntent pi = PendingIntent.getBroadcast(context.getApplicationContext(), notificationid, intent, PendingIntent.FLAG_ONE_SHOT);
        sf.edit().putInt("key", notificationid + 1).apply();
        AlarmManager am = (AlarmManager) context.getApplicationContext().getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, millis - 3600000, pi);
        AlarmManager am2 = (AlarmManager) context.getApplicationContext().getSystemService(ALARM_SERVICE);
        am2.set(AlarmManager.RTC_WAKEUP, millis - 900000, pi);
    }


    public static void canclenotifyme(Context context, String event_name) {
        Intent intent = new Intent(context, NotificationService.AlarmReceiver.class);
        int id = context.getSharedPreferences("notify", 0).getInt(event_name, 1);
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(sender);
        sender.cancel();
        context.getSharedPreferences("notify", 0).edit()
                .putInt(event_name, 0)
                .apply();
    }

}
