package com.soccer.api.soccer_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.soccer.api.soccer_app.com.soccer.api.soccer_app.model.Players;

import org.w3c.dom.Text;

public class PlayersActivity extends AppCompatActivity {

    TextView playerName, position, jerseyNumber, dateOfBirth, nationality, contractUntil, marketValue;
//    String pName, pPosition, pJerseyNumber, pDateOfBirth, pNationality, pContractUntil, pMarketValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        init();
    }

    private void init() {
        //Players object creation
        Players players = new Players();

        //Information from the players "Activity" when clicked
        playerName = (TextView) findViewById(R.id.playerName);
        position = (TextView) findViewById(R.id.position);
        jerseyNumber = (TextView) findViewById(R.id.jerseyNumber);
        dateOfBirth = (TextView) findViewById(R.id.dateOfBirth);
        nationality = (TextView) findViewById(R.id.nationality);
        contractUntil = (TextView) findViewById(R.id.contractUntil);
        marketValue = (TextView) findViewById(R.id.marketValue);


//        pName = getIntent().getStringExtra("playerName");
//        playerName.setText(pName);
//
//        pPosition = getIntent().getStringExtra("position");
//        position.setText(pPosition);
//
//        pJerseyNumber = getIntent().getStringExtra("jerseyNumber");
//        jerseyNumber.setText(pJerseyNumber);
//
//        pDateOfBirth = getIntent().getStringExtra("dateOfBirth");
//        dateOfBirth.setText(pDateOfBirth);
//
//        pNationality = getIntent().getStringExtra("nationality");
//        nationality.setText(pNationality);
//
//        pContractUntil = getIntent().getStringExtra("contractUntil");
//        contractUntil.setText(pContractUntil);
//
//        pMarketValue = getIntent().getStringExtra("marketValue");
//        marketValue.setText(pMarketValue);
    }


}
