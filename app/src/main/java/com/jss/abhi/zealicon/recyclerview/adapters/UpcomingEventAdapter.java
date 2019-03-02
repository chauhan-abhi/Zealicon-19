package com.jss.abhi.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailActivity;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.model.InnerData;

import java.util.ArrayList;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.UpcomingEventViewHolder> {

    public Context context;
    private ArrayList<EventData> upcomingEventScheduleArrayList;

    public class UpcomingEventViewHolder extends RecyclerView.ViewHolder{
        private TextView upcoming_event_name;
        private TextView event_time;
        private TextView event_location;
        private TextView event_category;
        private View upcoming_event_schedule_layout;


        public UpcomingEventViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            upcoming_event_name = itemView.findViewById(R.id.eventNameTV);
           // event_time = itemView.findViewById(R.id.timeTextView);
            //event_location= itemView.findViewById(R.id.locationTextView);
            upcoming_event_schedule_layout = itemView.findViewById(R.id.upcoming_event_schedule_layout);

            // event_category = itemView.findViewById(R.id.categoryTextView);
        }
    }

    public UpcomingEventAdapter(ArrayList<EventData> upcomingEventScheduleArrayList) {
        this.upcomingEventScheduleArrayList = upcomingEventScheduleArrayList;
    }

    @Override

    public UpcomingEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_event_layout,parent,false);
        return new UpcomingEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpcomingEventAdapter.UpcomingEventViewHolder holder, int position) {
        final EventData eventInnerData = upcomingEventScheduleArrayList.get(position);
        holder.upcoming_event_name.setText(eventInnerData.getName());
        //holder.event_time.setText(Integer.toString(eventInnerData.getEvent_time()));
   /* holder.event_location.setText((eventInnerData.getEvent_location()));
    holder.event_category.setText(eventInnerData.getCategory());*/

        holder.upcoming_event_schedule_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventData", eventInnerData);
                context.startActivity(intent);
            }
        });
    }

    @Override public int getItemCount() {
        return upcomingEventScheduleArrayList.size();
    }

}
