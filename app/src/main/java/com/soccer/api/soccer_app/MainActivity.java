package com.soccer.api.soccer_app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter.SoccerAdapter;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Teams;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView mTeamRecylerView;
    private SoccerAdapter mAdapter;
    private ArrayList<Teams> mTeamsCollections;

    //OnClick Listeners for activity
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent move = new Intent(MainActivity.this, TeamsActivity.class);
        init();
        mAdapter = new SoccerAdapter(mTeamsCollections, this, new SoccerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Teams teams, String uri, String teamName, String teamCode, String teamSN, String teamValue) {
//                Toast.makeText(MainActivity.this,"It works", Toast.LENGTH_SHORT).show();
                move.putExtra("image",uri);
                move.putExtra("team_name",teamName);
                move.putExtra("code", teamCode);
                move.putExtra("short_name", teamSN);
                move.putExtra("team_player", teams.getLinks());
                //move.putExtra("marketValue", teamValue);
                startActivity(move);
            }
        });
        mTeamRecylerView.setAdapter(mAdapter);
        new FetchData().execute();

    }

    private void init() {

        mTeamRecylerView = (RecyclerView)findViewById(R.id.items_list);
        mTeamRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mTeamRecylerView.setHasFixedSize(true);
        mTeamsCollections = new ArrayList<>();
    }

    //Class used to fetch data
    public class FetchData extends AsyncTask<Void, Void, Void>{

        //String used to soccer api uri
        private String mSoccerAPI;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            //API is consumed using the string located in values/strings.xml file.
            Uri builtUri = Uri.parse(getString(R.string.football_api));
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
                    //Array to hold the links
                    String [] links = new String[3];
                    String name;
                    String code;
                    String shortName;
                    String squadMarketValue;
                    String crestUrl;

                    JSONObject jTeams = (JSONObject) teamsArray.get(i);

                    JSONObject jLinks = jTeams.getJSONObject("_links");

                    name = jTeams.getString("name");
                    code = jTeams.getString("code");
                    shortName = jTeams.getString("shortName");
                    squadMarketValue = jTeams.getString("squadMarketValue");
                    crestUrl = jTeams.getString("crestUrl");
                    Log.v("Images", crestUrl);
                    //Testing links
                    JSONObject jSelf = jLinks.getJSONObject("self");
                    Log.v("Fixtures", jLinks.toString());
                    JSONObject jFixtures = jLinks.getJSONObject("fixtures");
                    JSONObject jPlayers = jLinks.getJSONObject("players");
                    //Log.v("Players", jPlayers.toString());


                    Teams teams = new Teams();
                    teams.setName(name);
                    teams.setCode(code);
                    teams.setLinks(links);
                    teams.setShortName(shortName);
                    teams.setSquadMarketValue(squadMarketValue);
                    teams.setCrestUrl(crestUrl);

                    //Adding items to collections team.
                    mTeamsCollections.add(teams);
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
            mAdapter.notifyDataSetChanged();
        }
    }
}
