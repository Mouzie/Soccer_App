package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Competition;

import java.util.ArrayList;

/**
 * Created by admin on 2017/09/05.
 */

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder> {

    private ArrayList<Competition> cData;
    private Activity cActivity;
    private final OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClickListener(Competition competition, int id, String caption, String league, String year, String currentMatchday, String numberOfMatchdays, String numberOfTeams, String numberOfGames, String lastUpdated);
    }

    public CompetitionAdapter(ArrayList<Competition> data, Activity activity, OnItemClickListener listener){
        this.cData = data;
        this.cActivity = activity;
        this.listener = listener;
    }


    @Override
    public CompetitionAdapter.CompetitionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CompetitionAdapter.CompetitionHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CompetitionHolder extends RecyclerView.ViewHolder {
        public CompetitionHolder(View itemView) {
            super(itemView);
        }
    }
}
