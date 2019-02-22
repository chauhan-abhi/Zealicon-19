package com.jss.abhi.zealicon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.InnerData;
import com.jss.abhi.zealicon.recyclerview.adapters.BookmarksEventAdapter;
import com.jss.abhi.zealicon.recyclerview.adapters.UpcomingEventAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView upcomingRecyclerView;
    RecyclerView bookmarksRecyclerView;
    ArrayList<InnerData> upcomingEventArrayList;
    ArrayList<InnerData> bookmarkEventArrayList;

    public static Fragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUpcomingEventData();
        initBookmarkEventData();

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

    public void initBookmarkEventData(){
        bookmarkEventArrayList = new ArrayList<>();
        bookmarkEventArrayList.add(new InnerData("Code in Pair"));
        bookmarkEventArrayList.add(new InnerData("Code in less"));
        bookmarkEventArrayList.add(new InnerData("Technovision"));
        bookmarkEventArrayList.add(new InnerData("Web-O-Cart"));
        bookmarkEventArrayList.add(new InnerData("Logocon"));
        bookmarkEventArrayList.add(new InnerData("Codeaggedon"));
        bookmarkEventArrayList.add(new InnerData("Coding is Divertido"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        upcomingRecyclerView = view.findViewById(R.id.upcomingRecyclerView);
        upcomingRecyclerView.setNestedScrollingEnabled(false);
        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        upcomingRecyclerView.setAdapter(new UpcomingEventAdapter(upcomingEventArrayList));

        bookmarksRecyclerView = view.findViewById(R.id.bookmarksRecyclerView);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        bookmarksRecyclerView.setAdapter(new BookmarksEventAdapter(bookmarkEventArrayList));
        return view;
    }
}