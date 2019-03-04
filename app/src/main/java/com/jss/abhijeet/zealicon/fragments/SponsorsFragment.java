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
import com.jss.abhijeet.zealicon.recyclerview.adapters.SponsorAdapter;

import java.util.ArrayList;

//import android.app.Fragment;

public class SponsorsFragment extends Fragment {

    private RecyclerView sponsorRecyclerView;
    private ArrayList<Developer> mSponsorList;
    String[] nameArray = new String[]{
            "HDFC Bank",
            "Glued Reloaded",
            "Ace Academy",
            "IOT Academy",
            "Prime Sweets",
            "Hutkar",
            "Kamat Rolls",
            "Cafe Yuva",
            "Sweet Corner",
            "Strawesome Mocktails and Drinks",
            "Wish a Cupcake",
            "Rio Drinks",
            "Bingo",
            "Joy Dil Se",
            "Royal Stylez Salon",
            "Aptron",
            "CETPA",
            "RCPL",
            "Power Finance Corporation",
            "ALKCE",
            "PANIT",
            "Pioneer One Honda",
            "GROG",
            "ATKT",
            "DU Beats"
    };
    String[] catArray = new String[]{
            "Title Sponsor",
            "Gaming Partner",
            "Education Partner",
            "Education Partner",
            "Food Partner",
            "Food Partner",
            "Food Partner",
            "Food Partner",
            "Food Partner",
            "Food Partner",
            "Food Partner",
            "Refreshment Partner",
            "Entertainment Partner",
            "Experience Partner",
            "Styling Partner",
            "Training Partner",
            "Training Partner",
            "Consultancy Partner",
            "Goodwill Partner",
            "Fashion Partner",
            "Fashion Partner",
            "Road Saftey Partner",
            "Pronite Partner",
            "Media Partner",
            "Media Partner"

    };

    public static Fragment newInstance() {
        SponsorsFragment fragment = new SponsorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSponsorData();

    }

    private void initSponsorData() {
        mSponsorList = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            mSponsorList.add(new Developer(nameArray[i], "", catArray[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        sponsorRecyclerView = view.findViewById(R.id.sponsor_rv);
        sponsorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sponsorRecyclerView.setAdapter(new SponsorAdapter(mSponsorList));
        return view;
    }


}