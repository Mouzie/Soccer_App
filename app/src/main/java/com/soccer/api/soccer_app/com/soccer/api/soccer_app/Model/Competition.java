package com.soccer.api.soccer_app.com.soccer.api.soccer_app.Model;

/**
 * Created by admin on 2017/09/05.
 */

public class Competition {
    private int id;
    private String caption;
    private String league;
    private String year;
    private String currentMatchday;
    private String numberOfMatchDays;
    private String numberOfTeams;
    private String numberOfGames;
    private String lastUpdated;

    public Competition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String competition) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(String currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public String getNumberOfMatchDays() {
        return numberOfMatchDays;
    }

    public void setNumberOfMatchDays(String numberOfMatchDays) {
        this.numberOfMatchDays = numberOfMatchDays;
    }

    public String getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(String numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public String getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(String numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
