package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.soccer.api.soccer_app.R;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter.CompetitionAdapter;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Competition;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CompetitionActivity extends AppCompatActivity {

    private RecyclerView cCompetitionRecylerView;
    private CompetitionAdapter cAdapter;
    private ArrayList<Competition> cCompetitionCollections;

    String competitionLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        
        final Intent competition_move = new Intent(CompetitionActivity.this, SingleCompetitionActivity.class);
        init();
        cAdapter = new CompetitionAdapter(cCompetitionCollections, this, new CompetitionAdapter.OnItemClickListener() {

            @Override
            public void onItemClickListener(Competition competition, String id, String caption, String league, String year, String currentMatchday, String numberOfMatchdays, String numberOfTeams, String numberOfGames, String lastUpdated) {
                competition_move.putExtra("ID", id);
                competition_move.putExtra("Caption", caption);
                competition_move.putExtra("League", league);
                competition_move.putExtra("Year", year);
                competition_move.putExtra("CurrentMatchDay", currentMatchday);
                competition_move.putExtra("NumberOfMatchDays", numberOfMatchdays);
                competition_move.putExtra("NumberOfTeams", numberOfTeams);
                competition_move.putExtra("NumberOfGames", numberOfGames);
                competition_move.putExtra("LastUpdate", lastUpdated);
                startActivity(competition_move);
            }
        });
        cCompetitionRecylerView.setAdapter(cAdapter);
        new FetchData().execute();
    }

    private void init() {
        cCompetitionRecylerView = (RecyclerView)findViewById(R.id.competition_layout);
        cCompetitionRecylerView.setLayoutManager(new LinearLayoutManager(this));
        cCompetitionRecylerView.setHasFixedSize(true);
        cCompetitionCollections = new ArrayList<>();
    }

    public class FetchData extends AsyncTask<Void, Void, Void> {

        private String mSoccerAPI;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(competitionLink);
            URL url;
            try{
                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token","6c088829710d4f96843247c63faaec45");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if(inputStream == null){
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null){
                    buffer.append(line + "\n");
                }

                if(buffer.length() == 0){
                    return null;
                }

                mSoccerAPI = buffer.toString();
                JSONObject jsonOBJ =  new JSONObject(mSoccerAPI);

                Log.v("Response", jsonOBJ.toString());

                JSONArray teamsArray = jsonOBJ.getJSONArray("teams");

                for(int i = 0; i < teamsArray.length(); i++){
                    Log.v("Success", i + "");
                    String [] links = new String[3];
                    String id;
                    String caption;
                    String league;
                    String year;
                    String currentMatchDay;
                    String numberOfMatchDays;
                    String numberOfTeams;
                    String numberOfGames;
                    String lastUpdate;

                    JSONObject jTeams = (JSONObject) teamsArray.get(i);
                    JSONObject jLinks = jTeams.getJSONObject("_links");

                    JSONObject jSelf = jLinks.getJSONObject("self");
                    JSONObject jFixtures = jLinks.getJSONObject("fixtures");
                    JSONObject jPlayers = jLinks.getJSONObject("players");
                    JSONObject jLogTable = jLinks.getJSONObject("leagueTable");

                    links[0] = jSelf.getString("href");
                    links[1] = jFixtures.getString("href");
                    links[2] = jPlayers.getString("href");
                    links[3] = jLogTable.getString("href");

                    id = jTeams.getString("id");
                    caption = jTeams.getString("caption");
                    league = jTeams.getString("league");
                    year = jTeams.getString("year");
                    currentMatchDay = jTeams.getString("currentMatchDay");
                    numberOfMatchDays = jTeams.getString("numberOfMatchDays");
                    numberOfTeams = jTeams.getString("numberOfTeams");
                    numberOfGames = jTeams.getString("numberOfGames");
                    lastUpdate = jTeams.getString("lastUpdate");

                    Competition competition = new Competition();
                    competition.setId(id);
                    competition.setCaption(caption);
                    competition.setLeague(league);
                    competition.setYear(year);
                    competition.setCurrentMatchday(currentMatchDay);
                    competition.setNumberOfMatchDays(numberOfMatchDays);
                    competition.setNumberOfTeams(numberOfTeams);
                    competition.setNumberOfGames(numberOfGames);
                    competition.setLastUpdated(lastUpdate);

                    cCompetitionCollections.add(competition);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            cAdapter.notifyDataSetChanged();
        }
    }
}
