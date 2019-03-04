package com.jss.abhijeet.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.activities.EventDetailActivity;
import com.jss.abhijeet.zealicon.model.EventData;

import java.util.ArrayList;

import static com.jss.abhijeet.zealicon.utils.TitleCaseConverter.toTitleCase;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.UpcomingEventViewHolder> {

    public Context context;
    private ArrayList<EventData> upcomingEventScheduleArrayList;

    public class UpcomingEventViewHolder extends RecyclerView.ViewHolder {
        private TextView upcoming_event_name;
        private TextView event_time;
        private TextView event_location;
        private TextView event_category;
        private TextView societyName;
        private CardView upcoming_event_schedule_layout;


        public UpcomingEventViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            upcoming_event_name = itemView.findViewById(R.id.eventNameTV);
            event_time = itemView.findViewById(R.id.eventTimeTV);
            event_location = itemView.findViewById(R.id.eventVenueTV);
            upcoming_event_schedule_layout = itemView.findViewById(R.id.upcoming_event_schedule_layout);
            societyName = itemView.findViewById(R.id.societyTV);
            // event_category = itemView.findViewById(R.id.categoryTextView);
        }
    }

    public UpcomingEventAdapter(ArrayList<EventData> upcomingEventScheduleArrayList) {
        this.upcomingEventScheduleArrayList = upcomingEventScheduleArrayList;
    }

    @Override

    public UpcomingEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_event_layout, parent, false);
        return new UpcomingEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UpcomingEventAdapter.UpcomingEventViewHolder holder, int position) {
        final EventData eventInnerData = upcomingEventScheduleArrayList.get(position);
        holder.upcoming_event_name.setText(toTitleCase(eventInnerData.getName()));
        holder.event_time.setText(eventInnerData.getTiming());
        if (eventInnerData.getSocietyId().equals("MMIL") || eventInnerData.getSocietyId().equals("ACE") ||
                eventInnerData.getSocietyId().equals("DSC") || eventInnerData.getSocietyId().equals("SPICE") || eventInnerData.getSocietyId().equals("YFAC")) {
            holder.societyName.setText(eventInnerData.getSocietyId());
        } else if (toTitleCase(eventInnerData.getSocietyId()).equals("Linguafranca")) {
            holder.societyName.setText("Lingua Franca");
        } else {
            holder.societyName.setText(toTitleCase(eventInnerData.getSocietyId()));
        }
        holder.event_location.setText(toTitleCase(eventInnerData.getVenue()));

        holder.upcoming_event_schedule_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventData", eventInnerData);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingEventScheduleArrayList.size();
    }

}
