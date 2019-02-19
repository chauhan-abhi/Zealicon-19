package com.jss.abhi.zealicon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.InnerData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;


public class ScheduleFragment extends Fragment {

    ArrayList<ArrayList> eventScheduleArrayListAl;
    ArrayList<InnerData> day1ScheduleArrayList;
    ArrayList<InnerData> day2ScheduleArrayList;
    ArrayList<InnerData> day3ScheduleArrayList;
    ArrayList<InnerData> day4ScheduleArrayList;



    public static Fragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
      return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScheduleData();

    }

    public void initScheduleData(){
        day1ScheduleArrayList = new ArrayList<>();
        day2ScheduleArrayList = new ArrayList<>();
        day3ScheduleArrayList = new ArrayList<>();
        day4ScheduleArrayList = new ArrayList<>();

        day1ScheduleArrayList.add(new InnerData("Code in Pair"));
        day1ScheduleArrayList.add(new InnerData("Code in less"));
        day1ScheduleArrayList.add(new InnerData("Technovision"));
        day1ScheduleArrayList.add(new InnerData("Web-O-Cart"));
        day1ScheduleArrayList.add(new InnerData("Logocon"));
        day1ScheduleArrayList.add(new InnerData("Codeaggedon"));
        day1ScheduleArrayList.add(new InnerData("Coding is Divertido"));

        day2ScheduleArrayList.add(new InnerData("Code in Pair"));
        day2ScheduleArrayList.add(new InnerData("Code in less"));
        day2ScheduleArrayList.add(new InnerData("Technovision"));
        day2ScheduleArrayList.add(new InnerData("Web-O-Cart"));
        day2ScheduleArrayList.add(new InnerData("Logocon"));
        day2ScheduleArrayList.add(new InnerData("Codeaggedon"));
        day2ScheduleArrayList.add(new InnerData("Coding is Divertido"));

        day3ScheduleArrayList.add(new InnerData("Code in Pair"));
        day3ScheduleArrayList.add(new InnerData("Code in less"));
        day3ScheduleArrayList.add(new InnerData("Technovision"));
        day3ScheduleArrayList.add(new InnerData("Web-O-Cart"));
        day3ScheduleArrayList.add(new InnerData("Logocon"));
        day3ScheduleArrayList.add(new InnerData("Codeaggedon"));
        day3ScheduleArrayList.add(new InnerData("Coding is Divertido"));

        day4ScheduleArrayList.add(new InnerData("Code in Pair"));
        day4ScheduleArrayList.add(new InnerData("Code in less"));
        day4ScheduleArrayList.add(new InnerData("Technovision"));
        day4ScheduleArrayList.add(new InnerData("Web-O-Cart"));
        day4ScheduleArrayList.add(new InnerData("Logocon"));
        day4ScheduleArrayList.add(new InnerData("Codeaggedon"));
        day4ScheduleArrayList.add(new InnerData("Coding is Divertido"));






    }
    private ScrollerViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        /*rv = view.findViewById(R.id.daysRecyclerView);*/
        //parseJSON();
        //initRecyclerView(outerData);
        viewPager = (ScrollerViewPager) view.findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) view.findViewById(R.id.indicator) ;
        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(EventScheduleFragment.class, getEventScheduleArrayList(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);

        return view;
    }

    private List<String> getTitles(){
        return Arrays.asList("Day 1", "Day 2", "Day 3", "Day 4");
    }

    private List<ArrayList> getEventScheduleArrayList(){
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

   /* void parseJSON() {
        SharedPreferences s = getContext().getSharedPreferences("events", 0);
        String day1array = s.getString(getString(R.string.day1events), getString(R.string.default_str));
        String day2array = s.getString(getString(R.string.day2events), getString(R.string.default_str));
        String day3array = s.getString(getString(R.string.day3events), getString(R.string.default_str));
        String day4array = s.getString(getString(R.string.day4events), getString(R.string.default_str));
        for (int i = 0; i < OUTER_COUNT; i++) {
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
            List<InnerData> innerDataList = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                InnerData innerData = new InnerData();
                try {
                    innerData = Jsonparser.toObject(jsonArray.getJSONObject(j).toString());
                    innerDataList.add(innerData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // innerDataList.add(new InnerData("CODERZ",12,"AB1",14));
            // innerDataList.add(new InnerData("Mechavoltz",13,"AB2",14));
            // innerDataList.add(new InnerData("Colaralo",15,"MPH",14));
            //innerData.add(new InnerData("Colaralo",16,"MPH",15));
            //innerData.add(new InnerData("Colaralo",12,"MPH",16));
            //innerData.add(new InnerData("Colaralo",13,"MPH",14));
            //innerData.add(new InnerData("Colaralo",15,"MPH",15));

            outerData.add(innerDataList);

        }
    }
    public class ParsingEvents extends AsyncTask<Void, Void, String> {
        HttpURLConnection conn;
        BufferedReader bufferedReader;
        String error;
        ParsingEvents() {

        }
        @Override
        protected String doInBackground(Void... params) {
            SharedPreferences s=getContext().getSharedPreferences("events",0);
            String day1array=s.getString(getString(R.string.day1events),getString(R.string.default_str));
            String day2array=s.getString(getString(R.string.day2events),getString(R.string.default_str));
            String day3array=s.getString(getString(R.string.day3events),getString(R.string.default_str));
            String day4array=s.getString(getString(R.string.day4events),getString(R.string.default_str));
            for(int i=0;i<OUTER_COUNT;i++){
                JSONArray jsonArray=new JSONArray();
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
                            jsonArray=new JSONArray();
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<InnerData> innerDataList = new ArrayList<>();
                for (int j=0;j<jsonArray.length();j++){
                    InnerData innerData=new InnerData();
                    try {
                        innerData=Jsonparser.toObject(jsonArray.getJSONObject(j).toString());
                        innerDataList.add(innerData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

               // innerDataList.add(new InnerData("CODERZ",12,"AB1",14));
               // innerDataList.add(new InnerData("Mechavoltz",13,"AB2",14));
               // innerDataList.add(new InnerData("Colaralo",15,"MPH",14));
                //innerData.add(new InnerData("Colaralo",16,"MPH",15));
                //innerData.add(new InnerData("Colaralo",12,"MPH",16));
                //innerData.add(new InnerData("Colaralo",13,"MPH",14));
                //innerData.add(new InnerData("Colaralo",15,"MPH",15));

                outerData.add(innerDataList);
            }
            return "";
        }
        @Override
        protected void onPostExecute(final String success) {
        }
    }*/
}
