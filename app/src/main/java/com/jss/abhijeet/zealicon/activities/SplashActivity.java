package com.jss.abhijeet.zealicon.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jss.abhijeet.zealicon.R;

import net.grandcentrix.tray.AppPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    boolean openHome = false;
    ConstraintLayout splashayout;
    ConstraintLayout noNetworkLayout;
    Button retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashayout = findViewById(R.id.loader_layout);
        noNetworkLayout = findViewById(R.id.no_internet_screen);
        retryButton = findViewById(R.id.retry_button);

        AppPreferences appPreferences = new AppPreferences(getApplicationContext());
        if (appPreferences.getInt("firsttime", 0) == 0) {
            requestjson();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!openHome) {
                        openHome = true;
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                    //finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

    private void requestjson() {
        final String backofficeUrl = "http://backoffice.zealicon.in/api/event";
        final String eventmanagerUrl = "http://18.188.126.83:8080/";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest eventRequest = new StringRequest(Request.Method.GET, backofficeUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.v("MyApp", response);
                        if (!response.toString().equals("[]") || !response.equals("")) {
                            try {
                                JSONObject resp = new JSONObject(response);
                                if (resp.getBoolean("success")) {
                                    JSONArray eventsarray = resp.getJSONArray("data");
                                    SharedPreferences s = getSharedPreferences("events", 0);
                                    s.edit().putString("allevents", eventsarray.toString()).apply();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        retry(error);
                    }
                });
        final StringRequest dataRequest = new StringRequest(Request.Method.GET, eventmanagerUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("EVENTMGR", response);
                        intializeSchedule(response);
                        AppPreferences appPreferences = new AppPreferences(getApplicationContext());
                        appPreferences.put("firsttime", 1);

                        if (!openHome) {
                            openHome = true;
                            Intent in = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(in);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        retry(error);
                    }
                });

        requestQueue.add(eventRequest);
        requestQueue.add(dataRequest);
        int socketTimeout = 2000;//20 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        eventRequest.setRetryPolicy(policy);
        dataRequest.setRetryPolicy(policy);
    }

    private void retry(VolleyError error) {
        Log.v("MyApp", error.toString());
        splashayout.setVisibility(View.GONE);
        noNetworkLayout.setVisibility(View.VISIBLE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splashayout.setVisibility(View.VISIBLE);
                noNetworkLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestjson();
                    }
                }, SPLASH_TIME_OUT);
            }
        });
    }

    void intializeSchedule(String response) {
        SharedPreferences s = getSharedPreferences("events", 0);
        String events = s.getString("allevents", "[]");
        JSONArray backofficeArray, eventsArray, day1Array, day2Array, day3Array, day4Array;
        day1Array = new JSONArray();
        day2Array = new JSONArray();
        day3Array = new JSONArray();
        day4Array = new JSONArray();
        try {
            backofficeArray = new JSONArray(events);
            eventsArray = new JSONArray(response);
            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject eventObject = eventsArray.getJSONObject(i);

                JSONObject optimised = new JSONObject();
                optimised.put("venue", eventObject.getString("venue"));
                optimised.put("_id", eventObject.get("_id"));
                optimised.put("name", eventObject.getString("name"));
                optimised.put("creatorname", eventObject.getString("creatorname"));
                optimised.put("date", eventObject.getString("date"));
                optimised.put("description", eventObject.getString("description"));
                for (int j = 0; j < backofficeArray.length(); j++) {
                    JSONObject backOfficeObject = backofficeArray.getJSONObject(j);
                    if (backOfficeObject.getString("name").equalsIgnoreCase(optimised.getString("name"))) {
                        optimised.put("contact_name", backOfficeObject.getString("contact_name"));
                        optimised.put("contact_no", backOfficeObject.get("contact_no"));
                        optimised.put("winner1", backOfficeObject.get("winner1"));
                        optimised.put("winner2", backOfficeObject.get("winner2"));
                        break;
                    }
                }
                String timeArray[] = getDate(eventObject.getString("date"));
                String day = timeArray[0];
                switch (day) {
                    case "03":
                    case "04":
                    case "05":
                        day1Array.put(optimised);
                        break;
                    case "06":
                        day2Array.put(optimised);
                        break;
                    case "07":
                        day3Array.put(optimised);
                        break;
                    case "08":
                        day4Array.put(optimised);
                        break;
                    default:
                }
            }
            //s.edit().putString("allevents", "[]").apply();
            s.edit().putString("day1events", day1Array.toString()).apply();
            s.edit().putString("day2events", day2Array.toString()).apply();
            s.edit().putString("day3events", day3Array.toString()).apply();
            s.edit().putString("day4events", day4Array.toString()).apply();
            Log.v("MyApp", "Schedule Distribution Done");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String[] getDate(String dateString) {
        //Tue Mar 05 2019 13:30:49 GMT+0530 (IST)
        //0123456789
        String date[] = new String[2];
        date[0] = dateString.substring(8, 10);
        date[1] = dateString.substring(16, 21);
        return date;
    }
}
