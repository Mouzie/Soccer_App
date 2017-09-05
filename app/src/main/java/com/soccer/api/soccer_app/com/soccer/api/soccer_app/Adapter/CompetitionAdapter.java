package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soccer.api.soccer_app.R;
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
        void onItemClickListener(Competition competition, String id, String caption, String league, String year, String currentMatchday, String numberOfMatchdays, String numberOfTeams, String numberOfGames, String lastUpdated);
    }

    public CompetitionAdapter(ArrayList<Competition> data, Activity activity, OnItemClickListener listener){
        this.cData = data;
        this.cActivity = activity;
        this.listener = listener;
    }

    @Override
    public CompetitionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixtures, parent, false);
        return new CompetitionAdapter.CompetitionHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionAdapter.CompetitionHolder holder, int position) {
        Competition competition = cData.get(position);
        holder.bind(competition, listener);
    }

    @Override
    public int getItemCount() {
        if(cData == null)
            return 0;
        return cData.size();    }

    public class CompetitionHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtCaption, txtLeague, txtYear, txtCurrentMatchday, txtNumberOfMatches, txtNumberOfTeames, txtNumberOfGames, txtLastUpdate;

        public CompetitionHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtCaption = itemView.findViewById(R.id.txtCaption);
            txtLeague = itemView.findViewById(R.id.txtLeague);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtCurrentMatchday = itemView.findViewById(R.id.txtCurrentMatchDay);
            txtNumberOfMatches = itemView.findViewById(R.id.txtNumberOfMatches);
            txtNumberOfTeames = itemView.findViewById(R.id.txtNumberOfTeams);
            txtNumberOfGames = itemView.findViewById(R.id.txtNumberOfGames);
            txtLastUpdate = itemView.findViewById(R.id.txtLastUpdate);
        }

        public void bind(final Competition competition, final OnItemClickListener listener) {
            txtId.setText(competition.getId());
            txtCaption.setText(competition.getCaption());
            txtLeague.setText(competition.getLeague());
            txtYear.setText(competition.getYear());
            txtCurrentMatchday.setText(competition.getCurrentMatchday());
            txtNumberOfMatches.setText(competition.getNumberOfMatchDays());
            txtNumberOfGames.setText(competition.getNumberOfGames());
            txtLastUpdate.setText(competition.getLastUpdated());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(competition,
                            competition.getId(),
                            competition.getCaption(),
                            competition.getLeague(),
                            competition.getYear(),
                            competition.getCurrentMatchday(),
                            competition.getNumberOfMatchDays(),
                            competition.getNumberOfTeams(),
                            competition.getNumberOfGames(),
                            competition.getLastUpdated());
                }
            });
        }
    }
}
