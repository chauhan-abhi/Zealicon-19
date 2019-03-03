package com.jss.abhi.zealicon.activities;

import android.Manifest;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.EventData;

import java.lang.reflect.Type;
import java.util.List;

import static com.jss.abhi.zealicon.utils.TitleCaseConverter.toTitleCase;

public class EventDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    private EventData eventData;
    private TextView eventTime, eventDate, eventVenue;
    private TextView eventDescription;
    private TextView prize1, prize2, contactName, contactNumber;
    private FloatingActionButton callButton, bookmarkButton;
    private Button eventRegisterButton;
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

        eventVenue = findViewById(R.id.locationTextView);
        eventDate = findViewById(R.id.eventDateTextView);
        eventDescription = findViewById(R.id.descriptionTextView);
        prize1 = findViewById(R.id.prize1);
        prize2 = findViewById(R.id.prize2);
        contactNumber = findViewById(R.id.organizerNumber1);
        contactName = findViewById(R.id.organizerName1);
        callButton = findViewById(R.id.callButton1);
        bookmarkButton = findViewById(R.id.bookmark_fab);
        eventRegisterButton = findViewById(R.id.event_register_button);
        eventTime = findViewById(R.id.eventTimeTV);

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
        contactName.setText(eventData.getContact_name());
        contactNumber.setText(eventData.getContact_no());
        eventVenue.setText(eventData.getVenue());
        eventDate.setText(eventData.getFullDate());
        eventTime.setText(eventData.getTiming());

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
                    bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark_border));

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
                    bookmarkButton.setImageDrawable(ContextCompat.getDrawable(EventDetailActivity.this, R.drawable.ic_bookmark));
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

        eventRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventRegisterActivityIntent = new Intent(EventDetailActivity.this, EventRegistrationActivity.class);
                eventRegisterActivityIntent.putExtra("eventData", eventData);
                startActivity(eventRegisterActivityIntent);
            }
        });
    }
}

