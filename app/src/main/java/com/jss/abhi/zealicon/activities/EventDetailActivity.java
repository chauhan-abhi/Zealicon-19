package com.jss.abhi.zealicon.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.service.NotificationService;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    private EventData eventData;
    private TextView eventTime, eventDate, eventVenue;
    private TextView eventDescription;
    private TextView prize1, prize2, contactName, contactNumber;
    private FloatingActionButton callButton, bookmarkButton;
    private boolean isBookMark = false;


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
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventDescription = (TextView) findViewById(R.id.descriptionTextView);
        prize1 = (TextView) findViewById(R.id.prize1);
        prize2 = (TextView) findViewById(R.id.prize2);
        contactNumber = (TextView) findViewById(R.id.organizerNumber1);
        contactName = (TextView) findViewById(R.id.organizerName1);
        callButton = (FloatingActionButton) findViewById(R.id.callButton1);
        bookmarkButton = (FloatingActionButton) findViewById(R.id.bookmark_fab);

        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.app_white));
        if (getIntent() != null) {
            eventData = (EventData) getIntent().getSerializableExtra("eventData");
        }

        isBookMark = getSharedPreferences("bookmarks", 0)
                .getInt(eventData.getId(), 0) != 0;
        if (isBookMark) {
            bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark));
        } else {
            bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark_border));
        }

        collapsingToolbarLayout.setTitle(toTitleCase(eventData.getName()));
        eventDescription.setText(eventData.getDescription());
        prize1.setText(String.format("₹ %s", eventData.getWinner1()));
        prize2.setText(String.format("₹ %s", eventData.getWinner2()));
        contactName.setText(toTitleCase(eventData.getContact_name()));
        contactNumber.setText(eventData.getContact_no());

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences s = getSharedPreferences("bookmarks", 0);
                String bookmarked_events = s.getString("list_bookmarked", "[]");
                Gson gson = new Gson();
                Type type = new TypeToken<List<EventData>>() {
                }.getType();

                if (isBookMark) {
                    // delete and unflag


                    List<EventData> oldArrayList = gson.fromJson(bookmarked_events, type);
                    for (EventData e : oldArrayList) {
                        if (e.getId().equals(eventData.getId())) {
                            oldArrayList.remove(e);
                        }
                    }
                    // unflag this event using event id to not show in ui
                    s.edit().putInt(eventData.getId(), 0).apply();
                    // add this event object to array list of bookmarks
                    s.edit().putString("list_bookmarked", gson.toJson(oldArrayList)).apply();
                    isBookMark = false;
                    bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark));

                    Toast.makeText(EventDetailActivity.this, "Event removed from Bookmarks", Toast.LENGTH_LONG).show();

                } else {
                    // add and show flag

                    List<EventData> oldArrayList = gson.fromJson(bookmarked_events, type);
                    oldArrayList.add(eventData);

                    //JSONArray bookMarkArray = new JSONArray(bookmarked_events);
                    //bookMarkArray.put(gson.toJson(eventData));
                    // flag this event using event id to show in ui
                    s.edit().putInt(eventData.getId(), 1).apply();
                    // add this event object to array list of bookmarks
                    s.edit().putString("list_bookmarked", gson.toJson(oldArrayList)).apply();
                    isBookMark = true;
                    bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark_border));
                    Toast.makeText(EventDetailActivity.this, "Event added to Bookmarks", Toast.LENGTH_LONG).show();



                }
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("onClick Call Fab", "" + eventData.getContact_no());
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(EventDetailActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            0);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + eventData.getContact_no()));
                startActivity(intent);
            }
        });


        /**
         * this function will be called when star or bell is pressed
         */

        //notifyme();


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
        am.set(AlarmManager.RTC_WAKEUP, millis - 3600000, pi);
        AlarmManager am2 = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am2.set(AlarmManager.RTC_WAKEUP, millis - 300000, pi);
    }

    private static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (c == '(') {
                    i++;
                }
                if (!Character.isWhitespace(builder.charAt(i))) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(builder.charAt(i)));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }
}

