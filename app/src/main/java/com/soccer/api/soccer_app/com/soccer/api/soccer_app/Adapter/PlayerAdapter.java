package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Players;

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

    public interface OnItemClickListener{
        void onItemClick(Players players,
                         String playerName,
                         String position,
                         String jerseyNumber, String dateOfBirth, String nationality, String contractUntil, String marketValue);
    }

    @Override
    public PlayerAdapter.PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.PlayerHolder holder, int position) {

        Players players = pData.get(position);
        holder.bind(players, listener);
    }

    @Override
    public int getItemCount() {
        if(pData == null)
            return 0;
        return pData.size();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder {

        TextView txtPlayerName, txtPosition , txtJerseyNumber, txtDateOfBirth, txtNationality ,txtContractUntil, txtMarketValue;

        public PlayerHolder(View itemView) {
            super(itemView);
            //Place holder for the players view
            txtPlayerName = itemView.findViewById(R.id.playerName);
            txtPosition = itemView.findViewById(R.id.position);
            txtJerseyNumber = itemView.findViewById(R.id.jerseyNumber);
            txtDateOfBirth = itemView.findViewById(R.id.dateOfBirth);
            txtNationality = itemView.findViewById(R.id.nationality);
            txtContractUntil = itemView.findViewById(R.id.contractUntil);
            txtMarketValue = itemView.findViewById(R.id.marketValue);
        }


        public void bind(final Players players, final OnItemClickListener listener){

            txtPlayerName.setText(players.getName());
            txtPosition.setText(players.getPosition());
            txtJerseyNumber.setText(players.getJerseyNumber());
            txtDateOfBirth.setText(players.getDateOfBirth());
            txtNationality.setText(players.getNationality());
            txtContractUntil.setText(players.getContractUntil());
            txtMarketValue.setText(players.getMarketValue());

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClick(players,
                            players.getName(),
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
