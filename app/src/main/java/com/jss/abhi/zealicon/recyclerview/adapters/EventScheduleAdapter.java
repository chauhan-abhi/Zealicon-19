package com.jss.abhi.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.EventDetailActivity;
import com.jss.abhi.zealicon.model.InnerData;

import java.util.ArrayList;

/**
 * Created by abhi on 15/2/18.
 */

public class EventScheduleAdapter extends RecyclerView.Adapter<EventScheduleAdapter.EventViewHolder> {

  public Context context;
  private ArrayList<? extends InnerData> eventScheduleArrayList;

  public class EventViewHolder extends RecyclerView.ViewHolder{
    private TextView event_name;
    private TextView event_time;
    private TextView event_location;
    private TextView event_category;
    private View event_schedule_layout;


    public EventViewHolder(View itemView) {
      super(itemView);
      context = itemView.getContext();

      event_name = itemView.findViewById(R.id.titleTextView);
      event_time = itemView.findViewById(R.id.timeTextView);
      event_location= itemView.findViewById(R.id.locationTextView);
      event_schedule_layout = itemView.findViewById(R.id.event_schedule_layout);

     // event_category = itemView.findViewById(R.id.categoryTextView);
    }
  }

  public EventScheduleAdapter(ArrayList<? extends InnerData> eventScheduleArrayList) {
    this.eventScheduleArrayList = eventScheduleArrayList;
  }

  @Override

  public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
    return new EventViewHolder(view);
  }

  @Override
    public void onBindViewHolder(EventScheduleAdapter.EventViewHolder holder, int position) {
    final InnerData eventInnerData = eventScheduleArrayList.get(position);
    holder.event_name.setText(eventInnerData.getEvent_name());
    //holder.event_time.setText(Integer.toString(eventInnerData.getEvent_time()));
   /* holder.event_location.setText((eventInnerData.getEvent_location()));
    holder.event_category.setText(eventInnerData.getCategory());*/

   holder.event_schedule_layout.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
       Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.list_item_click_animation);
       v.startAnimation(hyperspaceJumpAnimation);
       Intent intent = new Intent(context, EventDetailActivity.class);
       context.startActivity(intent);
     }
   });
  }

  @Override public int getItemCount() {
    return eventScheduleArrayList.size();
  }

}
