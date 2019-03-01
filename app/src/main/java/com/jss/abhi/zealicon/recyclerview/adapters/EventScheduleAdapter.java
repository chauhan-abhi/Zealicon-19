package com.jss.abhi.zealicon.recyclerview.adapters;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailActivity;
import com.jss.abhi.zealicon.model.EventData;
import com.jss.abhi.zealicon.service.NotificationService;
import com.jss.abhi.zealicon.utils.NotifierUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by abhi on 15/2/18.
 */

public class EventScheduleAdapter extends RecyclerView.Adapter<EventScheduleAdapter.EventViewHolder> {

    public Context context;
    private ArrayList<? extends EventData> eventScheduleArrayList;

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView event_name;
        private TextView event_time;
        private TextView event_location;
        private TextView event_category;
        private View event_schedule_layout;
        private ImageView bell_notify;


        public EventViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            event_name = itemView.findViewById(R.id.titleTextView);
            event_time = itemView.findViewById(R.id.timeTextView);
            event_location = itemView.findViewById(R.id.locationTextView);
            event_schedule_layout = itemView.findViewById(R.id.event_schedule_layout);
            event_category = itemView.findViewById(R.id.categoryTextView);
            bell_notify = itemView.findViewById(R.id.notify_bell_icon);
            // event_category = itemView.findViewById(R.id.categoryTextView);
        }
    }

    public EventScheduleAdapter(ArrayList<? extends EventData> eventScheduleArrayList) {
        this.eventScheduleArrayList = eventScheduleArrayList;
    }

    @Override

    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventScheduleAdapter.EventViewHolder holder, int position) {
        final EventData eventInnerData = eventScheduleArrayList.get(position);
        final int notifyId = context.getSharedPreferences("notify", 0).getInt(eventInnerData.getName(), 0);
        if (notifyId == 0) {
            holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.app_white), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.event_name.setText(toTitleCase(eventInnerData.getName()));
        //holder.event_category.setText(eventInnerData.getCategory_id());

        switch (Integer.valueOf(eventInnerData.getCategory_id())) {
            case 1:
                holder.event_category.setText("Coloralo");
                break;
            case 2:
                holder.event_category.setText("Mechavoltz");
                break;
            case 3:
                holder.event_category.setText("Play-it-on");
                break;
            case 4:
                holder.event_category.setText("Robotiles");
                break;
            case 5:
                holder.event_category.setText("Z-wars");
                break;
            case 6:
                holder.event_category.setText("Coderz");
                break;


        }
        //holder.event_time.setText(Integer.toString(eventInnerData.getEvent_time()));
   /* holder.event_location.setText((eventInnerData.getEvent_location()));
    holder.event_category.setText(eventInnerData.getCategory());*/

        holder.bell_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notifyId == 0) {
                    holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
                    NotifierUtil.notifyme(context, "01-03-2019 22:00", eventInnerData.getName());
                } else {
                    holder.bell_notify.setColorFilter(ContextCompat.getColor(context, R.color.app_white), android.graphics.PorterDuff.Mode.SRC_IN);
                    NotifierUtil.canclenotifyme(context, eventInnerData.getName());
                }
            }
        });
        holder.event_schedule_layout.setOnClickListener(new View.OnClickListener() {
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
        return eventScheduleArrayList.size();
    }

    private static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if(c == '('){
                    i++;
                }
                if (!Character.isWhitespace(builder.charAt(i))) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(builder.charAt(i)));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

}
