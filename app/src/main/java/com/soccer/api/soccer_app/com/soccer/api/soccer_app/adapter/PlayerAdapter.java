package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private final OnItemClickListener listener;

    public PlayerAdapter(ArrayList<Players> data, Activity activity, OnItemClickListener listener){
        this.pData = data;
        this.pActivity = activity;
        this.listener = listener;
    }

    //OnClick method that will pass player information to view
    public interface OnItemClickListener{
        void onItemClick(Players players, String playerName, String position, String jerseyNumber, String dateOfBirth, String nationality, String contractUntil, String marketValue);
    }

    @Override
    public PlayerAdapter.PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.PlayerHolder holder, int position) {

        //Using the Players class
        Players players = pData.get(position);

        //Method to bind
        holder.bind(players, listener);

        holder.setName(players.getPlayerName());
        holder.setPosition(players.getPosition());
        holder.setJerseryNumber(players.getJerseyNumber());
        holder.setDateOfBirth(players.getDateOfBirth());
        holder.setNationality(players.getNationality());
        holder.setMarketValue(players.getMarketValue());

    }

    @Override
    public int getItemCount() {
        if(pData == null)
            return 0;
        return pData.size();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder {

        TextView playerName, position , jerseyNumber, dateOfBirth, nationality ,contractUntil, marketValue;

        public PlayerHolder(View itemView) {
            super(itemView);
            //Place holder for the players view
            playerName = (TextView) itemView.findViewById(R.id.playerName);
            position = (TextView) itemView.findViewById(R.id.position);
            jerseyNumber = (TextView) itemView.findViewById(R.id.jerseyNumber);
            dateOfBirth = (TextView) itemView.findViewById(R.id.dateOfBirth);
            nationality = (TextView) itemView.findViewById(R.id.nationality);
            contractUntil = (TextView) itemView.findViewById(R.id.contractUntil);
            marketValue = (TextView) itemView.findViewById(R.id.marketValue);
        }

        public void setName(String playerName) {}
        public void setPosition(String position) {}
        public void setJerseryNumber(String jerseryNumber) {}
        public void setDateOfBirth(String dateOfBirth){}
        public void setNationality(String nationality){}
        public void setMarketValue(String marketValue){}

        //Binding elements to view
        public void bind(final Players players, final OnItemClickListener listener){
            playerName.setText(players.getPlayerName());
            position.setText(players.getPosition());
            jerseyNumber.setText(players.getJerseyNumber());
            dateOfBirth.setText(players.getDateOfBirth());
            nationality.setText(players.getNationality());
            contractUntil.setText(players.getContractUntil());
            marketValue.setText(players.getMarketValue());

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClick(players,
                            players.getPlayerName(),
                            players.getPosition(),
                            players.getJerseyNumber(),
                            players.getDateOfBirth(),
                            players.getNationality(),
                            players.getContractUntil(),
                            players.getMarketValue());
                }
            });

        }


    }
}
