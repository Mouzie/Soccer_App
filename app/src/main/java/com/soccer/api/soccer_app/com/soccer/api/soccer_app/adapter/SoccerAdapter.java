package com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter;

import android.app.Activity;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Fixtures;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Teams;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgDecoder;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgDrawable;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgSoftwareLayerSetter;

import java.io.InputStream;
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

    public interface OnItemClickListener {
        void onItemClick(Teams teams, String uri, String teamName, String teamCode, String teamSN, String teamValue);
    }

    @Override
    public SoccerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_layout, parent, false);
        return new SoccerHolder(view);
    }

    @Override
    public void onBindViewHolder(SoccerHolder holder, int position) {
        Teams teams = mData.get(position);

        holder.bind(teams, listener);

        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(mActivity)
                .using(Glide.buildStreamModelLoader(Uri.class, mActivity.getApplicationContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawable(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher_round)
                .load(Uri.parse(teams.getCrestUrl()))
                .into(holder.TeamsImgView);
    }


    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }


    public class SoccerHolder extends RecyclerView.ViewHolder {

        ImageView TeamsImgView;
        TextView TeamsTxtView;


        public SoccerHolder(View itemView) {
            super(itemView);

            //Content from Teams Layout
            TeamsImgView = (ImageView) itemView.findViewById(R.id.soccer_team);
            TeamsTxtView = (TextView) itemView.findViewById(R.id.team_name);

        }

        public void setName(String name) {
            TeamsTxtView.setText(name);
        }

        //Method called to aid with bind
        public void bind(final Teams teams, final OnItemClickListener listener) {
            TeamsImgView.setImageURI(Uri.parse(teams.getCrestUrl()));
            TeamsTxtView.setText(teams.getName());

            Glide.with(mActivity)
                    .load(teams.getCrestUrl())
                    .into(TeamsImgView);
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
