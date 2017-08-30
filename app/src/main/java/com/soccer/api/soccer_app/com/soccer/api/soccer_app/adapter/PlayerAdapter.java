package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Players;

import java.util.ArrayList;

/**
 * Created by admin on 2017/08/30.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    //Data from class players
    private ArrayList<Players> pData;
    private Activity pActivity;

    public PlayerAdapter(ArrayList<Players> data, Activity activity){
        this.pData = data;
        this.pActivity = activity;
    }

    @Override
    public PlayerAdapter.PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_players, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.PlayerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PlayerHolder extends RecyclerView.ViewHolder {
        public PlayerHolder(View itemView) {
            super(itemView);
        }
    }
}
