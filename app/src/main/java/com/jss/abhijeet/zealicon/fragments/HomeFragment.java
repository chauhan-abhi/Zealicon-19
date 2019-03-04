package com.jss.abhijeet.zealicon.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.model.EventData;
import com.jss.abhijeet.zealicon.recyclerview.adapters.BookmarksEventAdapter;
import com.jss.abhijeet.zealicon.recyclerview.adapters.UpcomingEventAdapter;
import com.jss.abhijeet.zealicon.utils.Jsonparser;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView upcomingRecyclerView;
    RecyclerView bookmarksRecyclerView;
    ArrayList<EventData> upcomingEventArrayList;
    ArrayList<EventData> bookmarkEventArrayList;
    BookmarksEventAdapter bookmarksEventAdapter;
    TextView noBookmarkTextView;
    TextView noUpcomingTextView;

    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        initBookmarkEventData();
        bookmarksEventAdapter.setData(bookmarkEventArrayList);
        initUpcomingEventData();
        if (bookmarkEventArrayList.size() == 0) {
            noBookmarkTextView.setVisibility(View.VISIBLE);
        } else {
            noBookmarkTextView.setVisibility((View.GONE));
        }
        if (upcomingEventArrayList.size() == 0) {
            noUpcomingTextView.setVisibility(View.VISIBLE);
        } else {
            noUpcomingTextView.setVisibility((View.GONE));
        }
    }

    private void initBookmarkEventData() {
        SharedPreferences s = getContext().getSharedPreferences("bookmarks", 0);
        String bookmarked_events = s.getString("list_bookmarked", "[]");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<EventData>>() {
        }.getType();

        List<EventData> oldArrayList = gson.fromJson(bookmarked_events, type);
        bookmarkEventArrayList = new ArrayList<>(oldArrayList);

    }

    public void initUpcomingEventData() {
        upcomingEventArrayList = new ArrayList<>();
        SharedPreferences s = getContext().getSharedPreferences("events", 0);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        String todaysEventString = "";
        switch (today) {
            case 3:
            case 4:
            case 5:
                todaysEventString = s.getString("day1events", "[]");
                break;
            case 6:
                todaysEventString = s.getString("day2events", "[]");
                break;
            case 7:
                todaysEventString = s.getString("day3events", "[]");
                break;
            case 8:
                todaysEventString = s.getString("day4events", "[]");
                break;
        }

        List<EventData> todayList = new ArrayList<>(Jsonparser.stringToEventArray(todaysEventString));


        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm a");
        try {
            Date date;
            for (EventData e : todayList) {
                date = formatter.parse(e.getFullDate() + " " + e.getTiming());
                if (date.getTime() - calendar.getTimeInMillis() < 3600000 * 4 && date.getTime() - calendar.getTimeInMillis() > 0) {
                    upcomingEventArrayList.add(e);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initBookmarkEventData();
        initUpcomingEventData();
        upcomingRecyclerView = view.findViewById(R.id.upcomingRecyclerView);
        noBookmarkTextView = view.findViewById(R.id.noBookmarkTextView);
        noUpcomingTextView = view.findViewById(R.id.noUpcomingTextView);
        if (bookmarkEventArrayList.size() != 0) {
            noBookmarkTextView.setVisibility(View.GONE);
        }
        if (upcomingEventArrayList.size() != 0) {
            noUpcomingTextView.setVisibility(View.GONE);
        }
        upcomingRecyclerView.setNestedScrollingEnabled(false);
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        upcomingRecyclerView.setAdapter(new UpcomingEventAdapter(upcomingEventArrayList));

        bookmarksRecyclerView = view.findViewById(R.id.bookmarksRecyclerView);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        bookmarksEventAdapter = new BookmarksEventAdapter(bookmarkEventArrayList);
        bookmarksRecyclerView.setAdapter(bookmarksEventAdapter);
        return view;
    }
}