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

public class BookmarksEventAdapter extends RecyclerView.Adapter<BookmarksEventAdapter.BookmarkEventScheduleViewHolder> {

    public Context context;
    private ArrayList<EventData> bookmarkEventScheduleArrayList;

    public class BookmarkEventScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView bookmark_event_name;
        private TextView event_time;
        private TextView event_date;
        private TextView event_location;
        private TextView bookmark_event_society;
        private CardView bookmark_event_schedule_layout;


        public BookmarkEventScheduleViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            bookmark_event_name = itemView.findViewById(R.id.titleTextView);
            event_time = itemView.findViewById(R.id.timeTextView);
            event_date = itemView.findViewById(R.id.dateTextView);
            event_location = itemView.findViewById(R.id.locationTextView);
            bookmark_event_schedule_layout = itemView.findViewById(R.id.bookmark_event_schedule_layout);
            bookmark_event_society = itemView.findViewById(R.id.societyTextView);
            // event_category = itemView.findViewById(R.id.categoryTextView);
        }
    }

    public BookmarksEventAdapter(ArrayList<EventData> bookmarkEventScheduleArrayList) {
        this.bookmarkEventScheduleArrayList = bookmarkEventScheduleArrayList;
    }

    @Override

    public BookmarkEventScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_event_layout, parent, false);
        return new BookmarkEventScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookmarkEventScheduleViewHolder holder, int position) {
        final EventData eventInnerData = bookmarkEventScheduleArrayList.get(position);
        holder.bookmark_event_name.setText(toTitleCase(eventInnerData.getName()));
        holder.event_time.setText(eventInnerData.getTiming());
        holder.event_date.setText(eventInnerData.getFullDate());
        holder.event_location.setText((toTitleCase(eventInnerData.getVenue())));
        if (eventInnerData.getSocietyId().equals("MMIL") || eventInnerData.getSocietyId().equals("ACE") ||
                eventInnerData.getSocietyId().equals("DSC") || eventInnerData.getSocietyId().equals("SPICE") || eventInnerData.getSocietyId().equals("YFAC")) {
            holder.bookmark_event_society.setText(eventInnerData.getSocietyId());
        } else if (toTitleCase(eventInnerData.getSocietyId()).equals("Linguafranca")) {
            holder.bookmark_event_society.setText("Lingua Franca");
        } else {
            holder.bookmark_event_society.setText(toTitleCase(eventInnerData.getSocietyId()));
        }

        holder.bookmark_event_schedule_layout.setOnClickListener(new View.OnClickListener() {
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
        return bookmarkEventScheduleArrayList.size();
    }

    public void setData(ArrayList<EventData> list) {
        bookmarkEventScheduleArrayList.clear();
        bookmarkEventScheduleArrayList.addAll(list);
        notifyDataSetChanged();
    }

}
