package com.soccer.api.soccer_app.com.soccer.api.soccer_app.model;

/**
 * Created by admin on 2017/09/04.
 */

public class Fixtures {

    private String date;
    private String status;
    private String matchDay;
    private String homeTeamName;
    private String awayTeamName;
    private String results;
    private String halftime;

    public Fixtures(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getHalftime() {
        return halftime;
    }

    public void setHalftime(String halftime) {
        this.halftime = halftime;
    }
}
