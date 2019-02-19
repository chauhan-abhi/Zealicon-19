package com.jss.abhi.zealicon.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.service.NotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Code in Pair");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.app_white));


        notifyme();


    }

    private void notifyme() {
        // String toParse = innerData.getTimings(); // Results in "2-5-2012 20:43"
        String toParse = "11-02-2019 11:40";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = new Date(); // You will need try/catch around this
        try {
            date = formatter.parse(toParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SharedPreferences sf = getSharedPreferences("notify", 0);
        int notificationid = sf.getInt("key", 1);
        sf.edit().putInt("Code in Pair", 1).apply();
        long millis = date.getTime();
        Intent intent = new Intent(getApplicationContext(), NotificationService.AlarmReceiver.class);
        intent.putExtra("keynotify", notificationid);
        intent.putExtra("eventname", "Code in Pair");
        Log.v("keynotify1", notificationid + "");

        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), notificationid, intent, PendingIntent.FLAG_ONE_SHOT);
        sf.edit().putInt("key", notificationid + 1).apply();
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, millis-3600000, pi);
        AlarmManager am2 = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am2.set(AlarmManager.RTC_WAKEUP, millis-300000, pi);
    }

}

