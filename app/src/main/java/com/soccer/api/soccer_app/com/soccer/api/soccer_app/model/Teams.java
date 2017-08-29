package com.soccer.api.soccer_app.com.soccer.api.soccer_app.model;

/**
 * Created by admin on 2017/08/28.
 */

public class Teams {

    private String [] links;
    private String name;
    private String shortName;
    private String code;
    private String squadMarketValue;
    private String crestUrl;
    private String self;
    private String fixtures;
    private String players;

    public Teams() {

    }
    public String [] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getFixtures() {
        return fixtures;
    }

    public void setFixtures(String fixtures) {
        this.fixtures = fixtures;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

}
