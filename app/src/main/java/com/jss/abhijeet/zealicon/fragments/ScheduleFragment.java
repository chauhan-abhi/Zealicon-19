package com.jss.abhijeet.zealicon.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.model.EventData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

import static com.jss.abhijeet.zealicon.utils.Jsonparser.stringToEventArray;


public class ScheduleFragment extends Fragment {

    ArrayList<ArrayList> eventScheduleArrayListAl;
    ArrayList<EventData> day1ScheduleArrayList;
    ArrayList<EventData> day2ScheduleArrayList;
    ArrayList<EventData> day3ScheduleArrayList;
    ArrayList<EventData> day4ScheduleArrayList;


    public static Fragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private ScrollerViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        /*rv = view.findViewById(R.id.daysRecyclerView);*/
        parseJSON();
        //initRecyclerView(outerData);
        viewPager = (ScrollerViewPager) view.findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) view.findViewById(R.id.indicator);
        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(EventScheduleFragment.class, getEventScheduleArrayList(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);

        return view;
    }

    private List<String> getTitles() {
        return Arrays.asList("Day 1", "Day 2", "Day 3", "Day 4");
    }

    private List<ArrayList> getEventScheduleArrayList() {
        eventScheduleArrayListAl = new ArrayList<>();
        eventScheduleArrayListAl.add(day1ScheduleArrayList);
        eventScheduleArrayListAl.add(day2ScheduleArrayList);
        eventScheduleArrayListAl.add(day3ScheduleArrayList);
        eventScheduleArrayListAl.add(day4ScheduleArrayList);

        return eventScheduleArrayListAl;

    }


    /*private void initRecyclerView(List<List<InnerData>> data) {
        ((RecyclerView.LayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data));
        new TailSnapHelper().attachToRecyclerView(rv);
    }*/

    void parseJSON() {
        SharedPreferences s = getContext().getSharedPreferences("events", 0);

        String day1array = s.getString(getString(R.string.day1events), getString(R.string.default_str));
        day1ScheduleArrayList = new ArrayList<>(stringToEventArray(day1array));

        String day2array = s.getString(getString(R.string.day2events), getString(R.string.default_str));
        day2ScheduleArrayList = new ArrayList<>(stringToEventArray(day2array));

        String day3array = s.getString(getString(R.string.day3events), getString(R.string.default_str));
        day3ScheduleArrayList = new ArrayList<>(stringToEventArray(day3array));

        String day4array = s.getString(getString(R.string.day4events), getString(R.string.default_str));
        day4ScheduleArrayList = new ArrayList<>(stringToEventArray(day4array));


      /*  for (int i = 0; i < 4; i++) {
            JSONArray jsonArray = new JSONArray();
            try {
                switch (i) {
                    case 0:
                        jsonArray = new JSONArray(day1array);
                        break;
                    case 1:
                        jsonArray = new JSONArray(day2array);
                        break;
                    case 2:
                        jsonArray = new JSONArray(day3array);
                        break;
                    case 3:
                        jsonArray = new JSONArray(day4array);
                        break;
                    default:
                        jsonArray = new JSONArray();
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            List<EventData> eventDataList = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                EventData eventData = new EventData();
                try {
                    eventData = Jsonparser.toObject(jsonArray.getJSONObject(j).toString());
                    eventDataList.add(eventData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            outerData.add(innerDataList);

        }*/
    }

}
