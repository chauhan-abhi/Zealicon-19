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
import com.jss.abhijeet.zealicon.recyclerview.adapters.TeamAdapter;

import java.util.ArrayList;

//import android.app.Fragment;

public class TeamFragment extends Fragment {

    private RecyclerView mTeamRecycler;
    private ArrayList<Developer> mTeamList;

    public static Fragment newInstance() {
        TeamFragment fragment = new TeamFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTeamData();
    }

    private void initTeamData() {
        mTeamList = new ArrayList<>();
        mTeamList.add(new Developer("Dr. Gurulingappa M Patil",
                "gurulingappa_patil",
                "Chairman", ""));
        mTeamList.add(new Developer("Dr. Prashant Chauhan",
                "prashant_chauhan",
                "Convener", "9990841257"));

        mTeamList.add(new Developer("Sonu Kumar Bharti",
                "sonu_bharti",
                "Festival Secretary", "9319446763"));

        mTeamList.add(new Developer("Satyam Verma",
                "satyam_verma",
                "Festival Co-Secretary", "9559870238"));
        mTeamList.add(new Developer("Satwik Singh",
                "satwik_singh",
                "Creative Head", "8853842111"));
        mTeamList.add(new Developer("Dhruv Srivastav",
                "dhruv_srivastav",
                "Technical Head", "9917475447"));
        mTeamList.add(new Developer("Aditya Rawat",
                "aditya_rawat",
                "Management Head", "8527371779"));
        mTeamList.add(new Developer("Ashutosh Atri",
                "ashutosh_atri",
                "Security Head", "9457177554"));
        mTeamList.add(new Developer("Waris",
                "waris",
                "Promotion & Marketing Head", "9643709668"));
        mTeamList.add(new Developer("Naman Jain",
                "naman_jain",
                "Dep. Security Head", "9721335607"));
        mTeamList.add(new Developer("Anurag Agarwal",
                "anurag_agarwal",
                "Sponsorship Head", "8743082849"));
        mTeamList.add(new Developer("Aabhas Pradhan",
                "aabhas_pradhan",
                "Dep. Sponsorship & Management Head", "8750668273"));
        mTeamList.add(new Developer("Ankit Gupta",
                "ankit_gupta",
                "Cultural Head", "8587974281"));
        mTeamList.add(new Developer("Ishan Dalela",
                "ishan_dalela",
                "Media & Photography Head", "8826384385"));
        mTeamList.add(new Developer("Rishabh Handa",
                "rishabh_handa",
                "Student Welafare Head", "9696016668"));
        mTeamList.add(new Developer("Hitwik Singh",
                "hitwik_singh",
                "Scheduling Head", "9958346697"));
        mTeamList.add(new Developer("Utkarsh Mishra",
                "utkarsh_mishra",
                "Merchandise Head", "8750668707"));
        mTeamList.add(new Developer("Sujata Bajaj",
                "sujata_bajaj",
                "Merchandise Head", "9354024413"));
        mTeamList.add(new Developer("Vineet Sharma",
                "vineet_sharma",
                "P.A. System Head", ""));
        mTeamList.add(new Developer("Abhay",
                "abhay",
                "College Promotion Head", "8505830434"));
        mTeamList.add(new Developer("Prashant",
                "prashant",
                "Resource Head", "7838018484"));
        mTeamList.add(new Developer("Vibhav Chaturvedi",
                "vibhav_chaturvedi",
                "Social Media & Marketing Head", "9454755102"));
        mTeamList.add(new Developer("Vinayak Bansal",
                "vinayak_bansal",
                "Social Media & Marketing Head", "7840050914"));
        mTeamList.add(new Developer("Sarvagya Singh",
                "sarvagya_singh",
                "Application Head", ""));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        mTeamRecycler = view.findViewById(R.id.team_rv);
        mTeamRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTeamRecycler.setAdapter(new TeamAdapter(mTeamList));
        return view;
    }


}