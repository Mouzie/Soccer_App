package com.soccer.api.soccer_app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.soccer.api.soccer_app.com.soccer.api.soccer_app.SinglePlayerActivity;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter.PlayerAdapter;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Players;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlayersActivity extends AppCompatActivity {

    private RecyclerView pPlayerRecylerView;
    private PlayerAdapter pAdapter;
    private ArrayList<Players> pPlayersCollections;

    TextView playerName, position, jerseyNumber, dateOfBirth, nationality, contractUntil, marketValue;
    String playerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        playerName = (TextView) findViewById(R.id.playerName);
        position = (TextView) findViewById(R.id.position);
        jerseyNumber = (TextView) findViewById(R.id.jerseyNumber);
        dateOfBirth = (TextView) findViewById(R.id.dateOfBirth);
        nationality = (TextView) findViewById(R.id.nationality);
        contractUntil = (TextView) findViewById(R.id.contractUntil);
        marketValue = (TextView) findViewById(R.id.marketValue);

        final Intent playersIntent = new Intent(PlayersActivity.this, SinglePlayerActivity.class);
        playerLink = getIntent().getStringExtra("team_player");
        init();
        new FetchData().execute();

        pAdapter = new PlayerAdapter(pPlayersCollections,this, new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Players players, String playerName, String position, String jerseyNumber, String dateOfBirth, String nationality, String contractUntil, String marketValue) {
                Toast.makeText(getApplicationContext(),"Player clicked",Toast.LENGTH_LONG);
                playersIntent.putExtra("Name", playerName);
                playersIntent.putExtra("Position", position);
                playersIntent.putExtra("JerseyNumber", jerseyNumber);
                playersIntent.putExtra("DateOfBirth", dateOfBirth);
                playersIntent.putExtra("Nationality", nationality);
                playersIntent.putExtra("ContractUntil", contractUntil);
                startActivity(playersIntent);
            }
        });

        pPlayerRecylerView.setAdapter(pAdapter);

    }

    private void init() {

        pPlayerRecylerView = (RecyclerView)findViewById(R.id.players_layout);
        pPlayerRecylerView.setLayoutManager(new LinearLayoutManager(this));
        pPlayerRecylerView.setHasFixedSize(true);
        pPlayersCollections = new ArrayList<>();
    }

    public class FetchData extends AsyncTask<Void, Void, Void> {

        private String mSoccerAPI;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.playerLink));
            URL url;
            try {
                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token", "6c088829710d4f96843247c63faaec45");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                mSoccerAPI = buffer.toString();
                JSONObject jsonOBJ = new JSONObject(mSoccerAPI);

                Log.v("Response", jsonOBJ.toString());

                JSONArray playersArray = jsonOBJ.getJSONArray("players");

                for (int i = 0; i < playersArray.length(); i++) {
                    Log.v("Success", i + "");
                    String playerName;
                    String position;
                    String jerseyNumber;
                    String dateOfBirth;
                    String nationality;
                    String contractUntil;

                    JSONObject jTeams = (JSONObject) playersArray.get(i);

                    Players players = new Players();
                    Log.v("Players", i + "");

                    playerName = jTeams.getString("name");
                    position = jTeams.getString("position");
                    jerseyNumber = jTeams.getString("jerseyNumber");
                    dateOfBirth = jTeams.getString("dateOfBirth");
                    nationality = jTeams.getString("nationality");
                    contractUntil = jTeams.getString("contractUntil");

                    players.setName(playerName);
                    players.setPosition(position);
                    players.setJerseyNumber(jerseyNumber);
                    players.setDateOfBirth(dateOfBirth);
                    players.setNationality(nationality);
                    players.setContractUntil(contractUntil);

                    pPlayersCollections.add(players);
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
            pAdapter.notifyDataSetChanged();
        }
    }
}
