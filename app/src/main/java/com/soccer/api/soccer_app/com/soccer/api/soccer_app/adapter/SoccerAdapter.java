package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
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
        return null;
    }

    @Override
    public void onBindViewHolder(SoccerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SoccerHolder extends RecyclerView.ViewHolder{

        //Items found in the teams layou
        ImageView TeamsImgView;
        TextView TeamsTxtView;
        TextView CoachTxtView;


        public SoccerHolder(View itemView) {
            super(itemView);

            //Content from Teams Layout
            TeamsImgView = (ImageView) itemView.findViewById(R.id.soccer_team);
            TeamsTxtView = (TextView) itemView.findViewById(R.id.team_name);
            CoachTxtView = (TextView) itemView.findViewById(R.id.team_coach);

        }

        public void setName(String name){ TeamsTxtView.setText(name);}
        public void setCoach(String coach) { CoachTxtView.setText(coach);}
    }

}
