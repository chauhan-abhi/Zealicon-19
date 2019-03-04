package com.jss.abhijeet.zealicon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.model.Developer;
import com.jss.abhijeet.zealicon.recyclerview.adapters.DeveloperAdapter;

import java.util.ArrayList;

//import android.app.Fragment;

public class DeveloperFragment extends Fragment {

    RecyclerView devRecyclerView;
    ArrayList<Developer> developerArrayList;

    public static Fragment newInstance() {
        DeveloperFragment fragment = new DeveloperFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDeveloperData();
    }

    public void initDeveloperData() {
        developerArrayList = new ArrayList<>();
        developerArrayList.add(new Developer("Abhijeet Singh Chauhan", "abhijeet",
                "App Developer", "https://github.com/chauhan-abhi/"));
        developerArrayList.add(new Developer("Divyanshu Agrawal", "divyanshu",
                "App Developer", "https://github.com/Divyansh42/"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_developer, container, false);
        devRecyclerView = view.findViewById(R.id.developer_rv);
        devRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        devRecyclerView.setAdapter(new DeveloperAdapter(developerArrayList));
        return view;
    }


}