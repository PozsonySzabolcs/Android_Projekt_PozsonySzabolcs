package com.example.retrofitplayers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Player {

    @SerializedName("teamId")
    @Expose
    private int teamId;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("jerseyNumber")
    @Expose
    private int jerseyNumber;

    /**
     * No args constructor for use in serialization
     *
     */
    public Player() {
    }

    /**
     *
     * @param teamName
     * @param teamId
     * @param name
     * @param jerseyNumber
     * @param position
     */
    public Player(int teamId, String name, String teamName, String position, int jerseyNumber) {
        super();
        this.teamId = teamId;
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    public Player(int teamId, String name, String position, int jerseyNumber) {
        this.teamId = teamId;
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    public Player(String name, String teamName, String position, int jerseyNumber) {
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String toString() {
        return "Player{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", position='" + position + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                '}';
    }

}