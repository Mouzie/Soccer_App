package com.soccer.api.soccer_app.com.soccer.api.soccer_app.AsyncTask;

import android.net.Uri;
import android.util.Log;

import com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model.Players;

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

/**
 * Created by admin on 2017/09/06.
 */

public class AsyncTask {
//    public class FetchData extends android.os.AsyncTask<Void, Void, Void> {
//
//        private String mSoccerAPI;
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            Uri builtUri = Uri.parse(playerLink);
//            URL url;
//            try {
//                url = new URL(builtUri.toString());
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.setRequestProperty("X-Auth-Token", "6c088829710d4f96843247c63faaec45");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    return null;
//                }
//
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//
//                if (buffer.length() == 0) {
//                    return null;
//                }
//
//                mSoccerAPI = buffer.toString();
//                JSONObject jsonOBJ = new JSONObject(mSoccerAPI);
//
//                Log.v("Response", jsonOBJ.toString());
//
//                JSONArray playersArray = jsonOBJ.getJSONArray("players");
//
//                for (int i = 0; i < playersArray.length(); i++) {
//                    Log.v("Success", i + "");
//                    String playerName;
//                    String position;
//                    String jerseyNumber;
//                    String dateOfBirth;
//                    String nationality;
//                    String contractUntil;
//
//                    JSONObject jTeams = (JSONObject) playersArray.get(i);
//
//                    Players players = new Players();
//                    Log.v("Players", i + "");
//
//                    playerName = jTeams.getString("name");
//                    position = jTeams.getString("position");
//                    jerseyNumber = jTeams.getString("jerseyNumber");
//                    dateOfBirth = jTeams.getString("dateOfBirth");
//                    nationality = jTeams.getString("nationality");
//                    contractUntil = jTeams.getString("contractUntil");
//
//                    players.setName(playerName);
//                    players.setPosition(position);
//                    players.setJerseyNumber(jerseyNumber);
//                    players.setDateOfBirth(dateOfBirth);
//                    players.setNationality(nationality);
//                    players.setContractUntil(contractUntil);
//
//                    pPlayersCollections.add(players);
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e("MainActivity", "Error closing stream", e);
//                    }
//                }
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void aVoid){
//            pAdapter.notifyDataSetChanged();
//        }
//    }
}
