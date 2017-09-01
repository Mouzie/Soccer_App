package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Teams;

import java.util.ArrayList;

/**
 * Created by admin on 2017/08/28.
 */

public class SoccerAdapter extends RecyclerView.Adapter<SoccerAdapter.SoccerHolder> {

    //Will hold the information from the class Teams
    private ArrayList<Teams> mData;
    private Activity mActivity;
    private final OnItemClickListener listener;

    public SoccerAdapter(ArrayList<Teams> data, Activity activity, OnItemClickListener listener) {
        this.mData = data;
        this.mActivity = activity;
        this.listener = listener;
    }

    //Implementing onClick for imageView
    public interface OnItemClickListener {
        void onItemClick(Teams teams, String uri, String teamName, String teamCode, String teamSN, String teamValue);
    }

    @Override
    public SoccerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //The new view will be returned once it's inflated
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_layout, parent, false);
        return new SoccerHolder(view);
    }

    @Override
    public void onBindViewHolder(SoccerHolder holder, int position) {
        //Getting data from the class Teams
        Teams teams = mData.get(position);

        holder.bind(teams, listener);
        holder.setName(teams.getName());
        holder.setCrestUrl(teams.getCrestUrl());

        //Bitmap Factory
//        Bitmap bitmap = BitmapFactory.decodeStream(openInputStream(teams.getCrestUrl()));
//        holder.TeamsImgView.setImageBitmap(bitmap);

        //Glide implementation for image view
//        Glide.with(mActivity)
//                //.resize(250, 250)
//                .load(teams.getCrestUrl())
//                .into(holder.TeamsImgView);

    }


    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }


    public class SoccerHolder extends RecyclerView.ViewHolder {

        //Items found in the teams layout
        ImageView TeamsImgView;
        TextView CodeTxtView, TeamsTxtView, ShortName, SquadeMarketValue;


        public SoccerHolder(View itemView) {
            super(itemView);

            //Content from Teams Layout
            TeamsImgView = (ImageView) itemView.findViewById(R.id.soccer_team);
            TeamsTxtView = (TextView) itemView.findViewById(R.id.team_name);

        }

        public void setName(String name) {
            TeamsTxtView.setText(name);
        }

        public void setCode(String code) {
            CodeTxtView.setText(code);
        }

        public void setCrestUrl(String crestUrl) {
            TeamsImgView.setImageURI(Uri.parse(crestUrl));
        }

        public void setShortName(String shortName) {
            ShortName.setText(shortName);
        }

        //Method called to aid with bind
        public void bind(final Teams teams, final OnItemClickListener listener) {
            TeamsImgView.setImageURI(Uri.parse(teams.getCrestUrl()));
            TeamsTxtView.setText(teams.getName());

            Glide.with(mActivity).load(teams.getCrestUrl()).into(TeamsImgView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(teams,
                            teams.getCrestUrl(),
                            teams.getName(),
                            teams.getCode(),
                            teams.getShortName(),
                            teams.getSquadMarketValue());
                }
            });
        }
    }

}
