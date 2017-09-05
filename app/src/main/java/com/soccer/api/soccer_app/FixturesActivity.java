package com.soccer.api.soccer_app;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.adapter.FixturesAdapter;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Fixtures;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgDecoder;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgDrawable;
import com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg.SvgSoftwareLayerSetter;

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

public class FixturesActivity extends AppCompatActivity {

    private RecyclerView tFixturesRecylerView;
    private FixturesAdapter tFixturesAdatpter;
    private ArrayList<Fixtures> tFixturesCollections;

    TextView txtDate, txtStatus, txtMatchday, txtHomeTeamName, txtAwayTeamName, txtResults, txtHalftime;
    String fixtureLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        txtDate = (TextView) findViewById(R.id.txtFixtureDate);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtMatchday = (TextView) findViewById(R.id.txtMatchDay);
        txtHomeTeamName = (TextView) findViewById(R.id.txtHomeTeamName);
        txtAwayTeamName = (TextView) findViewById(R.id.txtAwayTeamName);
        txtResults = (TextView) findViewById(R.id.txtResults);
        txtHalftime = (TextView) findViewById(R.id.txtHalfTime);

        final Intent fixtures_intent = new Intent(FixturesActivity.this, SingleFixtureActivity.class);
        fixtureLink = getIntent().getStringExtra("fixtures");
        init();
        new FetchData().execute();
        tFixturesAdatpter = new FixturesAdapter(tFixturesCollections,this, new FixturesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fixtures fixtures, String date, String status, String matchday, String homeTeamName, String awayTeamName, String results, String halftime) {
                Toast.makeText(getApplicationContext(),"Player clicked",Toast.LENGTH_LONG);
                fixtures_intent.putExtra("Date", date);
                fixtures_intent.putExtra("Matchday", matchday);
                fixtures_intent.putExtra("HomeTeamName", homeTeamName);
                fixtures_intent.putExtra("AwayTeamName", awayTeamName);
                fixtures_intent.putExtra("Results", results);
                fixtures_intent.putExtra("Halftime", halftime);
                startActivity(fixtures_intent);
            }
        });

        tFixturesRecylerView.setAdapter(tFixturesAdatpter);

        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri.class, getApplicationContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawable(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());
    }

    private void init() {
        tFixturesRecylerView = (RecyclerView)findViewById(R.id.layout_fixture);
        tFixturesRecylerView.setLayoutManager(new LinearLayoutManager(this));
        tFixturesRecylerView.setHasFixedSize(true);
        tFixturesCollections = new ArrayList<>();
    }


    public class FetchData extends AsyncTask<Void, Void, Void> {

        private String mSoccerAPI;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(fixtureLink);
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

                JSONArray fixturesArray = jsonOBJ.getJSONArray("fixtures");

                for (int i = 0; i < fixturesArray.length(); i++) {
                    Log.v("Success", i + "");
                    String date;
                    String status;
                    String matchday;
                    String homeTeamName;
                    String awayTeamName;
                    String results;
                    String halftime;

                    JSONObject jTeams = (JSONObject) fixturesArray.get(i);

                    Fixtures fixtures = new Fixtures();
                    Log.v("Fixtures", fixturesArray + "");

                    date = jTeams.getString("date");
                    status = jTeams.getString("status");
                    matchday = jTeams.getString("matchday");
                    homeTeamName = jTeams.getString("homeTeamName");
                    awayTeamName = jTeams.getString("awayTeamName");
                    results = jTeams.getString("results");
                    halftime = jTeams.getString("halftime");

                    fixtures.setDate(date);
                    fixtures.setStatus(status);
                    fixtures.setMatchDay(matchday);
                    fixtures.setHomeTeamName(homeTeamName);
                    fixtures.setAwayTeamName(awayTeamName);
                    fixtures.setResults(results);
                    fixtures.setHalftime(halftime);

                    tFixturesCollections.add(fixtures);
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
            tFixturesAdatpter.notifyDataSetChanged();
        }
    }
}
