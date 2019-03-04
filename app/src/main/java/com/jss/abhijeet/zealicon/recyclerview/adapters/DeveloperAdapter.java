package com.jss.abhijeet.zealicon.recyclerview.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.model.Developer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abhi on 15/2/18.
 */

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DevViewHolder> {

    private Context context;
    private ArrayList<Developer> developerArrayList;

    public class DevViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImg;
        private TextView name, position;
        private ImageView gitFab;


        public DevViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            profileImg = itemView.findViewById(R.id.devImageView);
            name = itemView.findViewById(R.id.nameTextView);
            position = itemView.findViewById(R.id.positionTextView);
            gitFab = itemView.findViewById(R.id.githubImageView);
        }
    }

    public DeveloperAdapter(ArrayList<Developer> developerArrayList) {
        this.developerArrayList = developerArrayList;
    }

 /* public void addDeveloper(List<Developer> developerList){
    this.developerArrayList.addAll(developerList);
    notifyDataSetChanged();
  }*/

    @Override

    public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_developer_member, parent, false);
        return new DevViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeveloperAdapter.DevViewHolder holder, int position) {
        final Developer developer = developerArrayList.get(position);
        holder.name.setText(developer.getName());
        holder.position.setText(developer.getPosition());
        //holder.profileImg.setImageResource(R.drawable.avatar);
        if (!(developer.getImgurl().isEmpty() || developer.getImgurl() == null)) {
            switch (position) {
                case 0:
                    holder.profileImg.setImageResource(R.drawable.abhijeet);
                    break;
                case 1:
                    holder.profileImg.setImageResource(R.drawable.divyanshu);
                    break;
            }
        } else {
            holder.profileImg.setImageResource(R.drawable.aavatar);
        }

        holder.gitFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(developer.getGitUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return developerArrayList.size();
    }
}
