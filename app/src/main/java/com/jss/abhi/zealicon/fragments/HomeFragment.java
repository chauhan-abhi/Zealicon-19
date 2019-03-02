package com.jss.abhi.zealicon.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.model.InnerData;
import com.jss.abhi.zealicon.recyclerview.adapters.BookmarksEventAdapter;
import com.jss.abhi.zealicon.recyclerview.adapters.UpcomingEventAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.jss.abhi.zealicon.utils.Jsonparser.stringToEventArray;

public class HomeFragment extends Fragment {

    RecyclerView upcomingRecyclerView;
    RecyclerView bookmarksRecyclerView;
    ArrayList<InnerData> upcomingEventArrayList;
    ArrayList<EventData> bookmarkEventArrayList;
    BookmarksEventAdapter bookmarksEventAdapter;

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

    public void initUpcomingEventData(){

        upcomingEventArrayList = new ArrayList<>();
        upcomingEventArrayList.add(new InnerData("Code in Pair"));
        upcomingEventArrayList.add(new InnerData("Code in less"));
        upcomingEventArrayList.add(new InnerData("Technovision"));
        upcomingEventArrayList.add(new InnerData("Web-O-Cart"));
        upcomingEventArrayList.add(new InnerData("Logocon"));
        upcomingEventArrayList.add(new InnerData("Codeaggedon"));
        upcomingEventArrayList.add(new InnerData("Coding is Divertido"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        initBookmarkEventData();
        initUpcomingEventData();
        upcomingRecyclerView = view.findViewById(R.id.upcomingRecyclerView);
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