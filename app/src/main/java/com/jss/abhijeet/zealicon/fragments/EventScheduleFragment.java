package com.jss.abhijeet.zealicon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.model.EventData;
import com.jss.abhijeet.zealicon.recyclerview.adapters.EventScheduleAdapter;

import java.util.ArrayList;

public class EventScheduleFragment extends Fragment {

    private ArrayList<? extends EventData> eventScheduleArrayList;
    private RecyclerView eventScheduleRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventScheduleArrayList = getArguments().getParcelableArrayList("data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventScheduleRv = view.findViewById(R.id.event_schedule_rv);
        eventScheduleRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventScheduleRv.setAdapter(new EventScheduleAdapter(eventScheduleArrayList));

    }
}
