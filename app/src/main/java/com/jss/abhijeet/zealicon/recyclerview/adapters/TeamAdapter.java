package com.jss.abhijeet.zealicon.recyclerview.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.activities.MainActivity;
import com.jss.abhijeet.zealicon.model.Developer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abhi on 19/2/18.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private ArrayList<Developer> mTeamList;
    private Context context;

    public class TeamViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImg;
        private TextView name, position;
        private ImageView callImageview;

        public TeamViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            profileImg = itemView.findViewById(R.id.teamImageView);
            name = itemView.findViewById(R.id.memberName);
            position = itemView.findViewById(R.id.memberPosition);
            callImageview = itemView.findViewById(R.id.call);
        }
    }

    public TeamAdapter(ArrayList<Developer> mTeamList) {
        this.mTeamList = mTeamList;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_team_member, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        final Developer team = mTeamList.get(position);
        holder.name.setText(team.getName());
        holder.position.setText(team.getPosition());
        //holder.profileImg.setImageResource(R.drawable.avatar);
      /*  if (!(team.getImgurl().isEmpty() || team.getImgurl() == null)) {
            if (team.getImgurl().equals("gurulingappa_patil")) {
                holder.profileImg.setImageResource(R.drawable.gurulingappa_patil);
            } else if (team.getImgurl().equals("prashant_chauhan"))
                holder.profileImg.setImageResource(R.drawable.prashant_chauhan);
            else if (team.getImgurl().equals("sonu_bharti"))
                holder.profileImg.setImageResource(R.drawable.sonu_kumar);
            else if (team.getImgurl().equals("satyam_verma"))
                holder.profileImg.setImageResource(R.drawable.satyam_verma);
            else if (team.getImgurl().equals("satwik_singh"))
                holder.profileImg.setImageResource(R.drawable.satwik_singh);
            else if (team.getImgurl().equals("dhruv_srivastav"))
                holder.profileImg.setImageResource(R.drawable.dhruv_srivastava);
            else if (team.getImgurl().equals("aditya_rawat"))
                holder.profileImg.setImageResource(R.drawable.aditya_rawat);
            else if (team.getImgurl().equals("ashutosh_atri"))
                holder.profileImg.setImageResource(R.drawable.ashutosh_atri);
            else if (team.getImgurl().equals("waris"))
                holder.profileImg.setImageResource(R.drawable.waris);
            else if (team.getImgurl().equals("naman_jain"))
                holder.profileImg.setImageResource(R.drawable.naman_jain);
            else if (team.getImgurl().equals("anurag_agarwal"))
                holder.profileImg.setImageResource(R.drawable.anurag_agarwal);
            else if (team.getImgurl().equals("aabhas_pradhan"))
                holder.profileImg.setImageResource(R.drawable.abhas_pradhan);
            else if (team.getImgurl().equals("ankit_gupta"))
                holder.profileImg.setImageResource(R.drawable.ankit_gupta);
            else if (team.getImgurl().equals("ishan_dalela"))
                holder.profileImg.setImageResource(R.drawable.ishan_dalela);
            else if (team.getImgurl().equals("rishabh_handa"))
                holder.profileImg.setImageResource(R.drawable.rishabh_handa);
            else if (team.getImgurl().equals("hitwik_singh"))
                holder.profileImg.setImageResource(R.drawable.hitwik_singh);
            else if (team.getImgurl().equals("utkarsh_mishra"))
                holder.profileImg.setImageResource(R.drawable.utkarsh_mishra);
            else if (team.getImgurl().equals("sujata_bajaj"))
                holder.profileImg.setImageResource(R.drawable.sujata_bajaj);
            else if (team.getImgurl().equals("vineet_sharma")) {
                holder.profileImg.setImageResource(R.drawable.vineet_sharma);
            } else if (team.getImgurl().equals("abhay"))
                holder.profileImg.setImageResource(R.drawable.abhay);
            else if (team.getImgurl().equals("prashant"))
                holder.profileImg.setImageResource(R.drawable.prashant);
            else if (team.getImgurl().equals("vibhav_chaturvedi"))
                holder.profileImg.setImageResource(R.drawable.vibhav_chaturvedi);
            else if (team.getImgurl().equals("vinayak_bansal"))
                holder.profileImg.setImageResource(R.drawable.vinayak_bansal);
            else if (team.getImgurl().equals("sarvagya_singh")) {
                holder.profileImg.setImageResource(R.drawable.sarvagya_singh);
            } else
                holder.profileImg.setImageResource(R.drawable.aavatar);
        } else {
            holder.profileImg.setImageResource(R.drawable.aavatar);
        }*/

        holder.callImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("onClick Call Fab", "" + team.getMobNum());
                if (team.getMobNum() == "") {
                    Toast.makeText(context, "Sorry! Number not disclosed", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions((MainActivity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            0);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + team.getMobNum()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }


}
