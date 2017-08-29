package com.soccer.api.soccer_app;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        new FetchData().execute();
    }

    private void init() {
        mTeamRecylerView = (RecyclerView)findViewById(R.id.teams_recylcer_view);
        mTeamRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mTeamRecylerView.setHasFixedSize(true);
        mTeamsCollections = new ArrayList<>();
        mAdapter = new SoccerAdapter(mTeamsCollections, this);
        mTeamRecylerView.setAdapter(mAdapter);
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
                urlConnection.setRequestProperty("user-key","1a7000e80c0f423f844dd6e4e6e74418");
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
                    Log.v("Team", i + "");
                    String name;
                    String coach;
                    String crestUrl;

                    JSONObject jTeams = (JSONObject) teamsArray.get(i);
                    jTeams = jTeams.getJSONObject("teams");

                    name = jTeams.getString("name");
                    coach = jTeams.getString("coach");
                    crestUrl = jTeams.getString("crestUrl");

                    Teams teams = new Teams();
                    teams.setName(name);
                    teams.setCoach(coach);
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
