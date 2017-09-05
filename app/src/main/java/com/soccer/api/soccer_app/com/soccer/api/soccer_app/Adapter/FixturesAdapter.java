package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Fixtures;

import java.util.ArrayList;

/**
 * Created by admin on 2017/09/04.
 */

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.FixturesHolder> {

    private ArrayList<Fixtures> fData;
    private Activity fActivity;
    private final OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Fixtures fixtures, String date, String status, String matchDay, String homeTeamName, String awayTeamName, String[] results, String[] halftime);
    }

    public FixturesAdapter(ArrayList<Fixtures> data, Activity activity, OnItemClickListener listener) {
        this.fData = data;
        this.fActivity = activity;
        this.listener = listener;
    }

    @Override
    public FixturesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixtures, parent, false);
        return new FixturesHolder(view);
    }

    @Override
    public void onBindViewHolder(FixturesHolder holder, int position) {
        Fixtures fixtures = fData.get(position);
        holder.bind(fixtures, listener);

    }

    @Override
    public int getItemCount() {
        if(fData == null)
            return 0;
        return fData.size();
    }

    public class FixturesHolder extends RecyclerView.ViewHolder {

        TextView txtDate, txtStatus, txtMatchday, txtHomeTeamName, txtAwayTeamName, txtResults, txtHalftime;

        public FixturesHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtFixtureDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtMatchday = itemView.findViewById(R.id.txtMatchDay);
            txtHomeTeamName = itemView.findViewById(R.id.txtHomeTeamName);
            txtAwayTeamName = itemView.findViewById(R.id.txtAwayTeamName);
            txtResults = itemView.findViewById(R.id.txtResults);
            txtHalftime = itemView.findViewById(R.id.txtHalfTime);
        }

        public void bind(final Fixtures fixtures, final FixturesAdapter.OnItemClickListener listener) {
            txtDate.setText(fixtures.getDate());
            txtStatus.setText(fixtures.getStatus());
            txtMatchday.setText(fixtures.getMatchDay());
            txtHomeTeamName.setText(fixtures.getHomeTeamName());
            txtAwayTeamName.setText(fixtures.getAwayTeamName());
            txtResults.setText(fixtures.getResults()[0]+" - "+fixtures.getResults()[1]);
            txtHalftime.setText(fixtures.getHalftime()[0] +" - "+fixtures.getHalftime()[1]);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fixtures,
                            fixtures.getDate(),
                            fixtures.getStatus(),
                            fixtures.getMatchDay(),
                            fixtures.getHomeTeamName(),
                            fixtures.getAwayTeamName(),
                            fixtures.getResults(),
                            fixtures.getHalftime());
                }
            });
        }
    }
}
