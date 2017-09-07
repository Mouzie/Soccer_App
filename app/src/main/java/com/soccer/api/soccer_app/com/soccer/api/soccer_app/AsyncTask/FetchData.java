//package com.soccer.api.soccer_app.com.soccer.api.soccer_app.AsyncTask;
//
//import android.content.res.Resources;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.soccer.api.soccer_app.R;
//import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Adapter.PlayerAdapter;
//import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Players;
//import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Teams;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
///**
// * Created by admin on 2017/09/06.
// */
//
//public class FetchData extends AsyncTask<Void, Void, RecyclerView.Adapter> {
//
//    private ArrayList<Players> pPlayersCollections;
//    RecyclerView.Adapter mAdapter;
//    private String mSoccerAPI;
//    AdapterCallback callback;
//    String api = "";
//
//    public interface AdapterCallback{
//        void onCallback(RecyclerView.Adapter adapter);
//    }
//
//    public FetchData(String API, RecyclerView.Adapter adapter, AdapterCallback callback) {
//        this.mAdapter = adapter;
//        this.api = API;
//        this.pPlayersCollections = new ArrayList<>();
//        this.callback = callback;
//    }
//
//    @Override
//    protected RecyclerView.Adapter doInBackground(Void... voids) {
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        Uri builtUri = Uri.parse("http://api.football-data.org/v1/teams/346/players");
//        URL url;
//        try {
//            url = new URL(builtUri.toString());
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setRequestProperty("X-Auth-Token", "6c088829710d4f96843247c63faaec45");
//            urlConnection.connect();
//
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                return null;
//            }
//
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0) {
//                return null;
//            }
//
//            mSoccerAPI = buffer.toString();
//            JSONObject jsonOBJ = new JSONObject(mSoccerAPI);
//            Log.v("Response", jsonOBJ.toString());
//            JSONArray playersArray = jsonOBJ.getJSONArray("players");
//            for (int i = 0; i < playersArray.length(); i++) {
//                Log.v("Success", i + "");
//                String playerName;
//                String position;
//                String jerseyNumber;
//                String dateOfBirth;
//                String nationality;
//                String contractUntil;
//                JSONObject jTeams = (JSONObject) playersArray.get(i);
//                Players players = new Players();
//                Log.v("Players", i + "");
//                playerName = jTeams.getString("name");
//                position = jTeams.getString("position");
//                jerseyNumber = jTeams.getString("jerseyNumber");
//                dateOfBirth = jTeams.getString("dateOfBirth");
//                nationality = jTeams.getString("nationality");
//                contractUntil = jTeams.getString("contractUntil");
//                players.setName(playerName);
//                players.setPosition(position);
//                players.setJerseyNumber(jerseyNumber);
//                players.setDateOfBirth(dateOfBirth);
//                players.setNationality(nationality);
//                players.setContractUntil(contractUntil);
//
//                pPlayersCollections.add(players);
//
//                mAdapter = new PlayerAdapter(pPlayersCollections);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e("MainActivity", "Error closing stream", e);
//                }
//            }
//        }
//        return mAdapter;
//    }
//    @Override
//    protected void onPostExecute(RecyclerView.Adapter aVoid){
//        super.onPostExecute(aVoid);
//        callback.onCallback(aVoid);
//        Log.v("Players", "in Adapter.notifyDataSetChanged();");
//    }
//}
