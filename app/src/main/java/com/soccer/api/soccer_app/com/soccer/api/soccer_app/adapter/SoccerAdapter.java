package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Teams;

import java.util.ArrayList;

/**
 * Created by admin on 2017/08/28.
 */

public class SoccerAdapter extends RecyclerView.Adapter<SoccerAdapter.SoccerHolder > {

    //Will hold the information from the class Teams
    private ArrayList<Teams> mData;
    private Activity mActivity;

    public SoccerAdapter(ArrayList<Teams> data, Activity activity) {
        this.mData = data;
        this.mActivity = activity;
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

        //Holder will hold set both team and coach names.
        holder.setName(teams.getName());
        holder.setCode(teams.getCode());
        holder.setName(teams.getShortName());
        holder.setSquadeMarketValue(String.valueOf(teams.getSquadMarketValue()));
        holder.setCrestUrl(teams.getCrestUrl());


    }

    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;
        return mData.size();
    }

    public class SoccerHolder extends RecyclerView.ViewHolder{

        //Items found in the teams layout
        ImageView TeamsImgView;
        TextView CodeTxtView, squadeMarketValueView, TeamsTxtView;


        public SoccerHolder(View itemView) {
            super(itemView);

            //Content from Teams Layout
            TeamsImgView = (ImageView) itemView.findViewById(R.id.soccer_team);
            TeamsTxtView = (TextView) itemView.findViewById(R.id.team_name);
            CodeTxtView = (TextView) itemView.findViewById(R.id.team_code);
            squadeMarketValueView = (TextView) itemView.findViewById(R.id.squadMarketValue);

        }

        public void setName(String name){ TeamsTxtView.setText(name);}
        public void setCode(String code) { CodeTxtView.setText(code);}
        public void setSquadeMarketValue(String squadeMarketValue) { squadeMarketValueView.setText(squadeMarketValue);}
        public void setCrestUrl(String crestUrl){TeamsImgView.setImageURI(Uri.parse(crestUrl)); }
    }

}
