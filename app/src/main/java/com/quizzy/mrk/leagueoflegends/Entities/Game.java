package com.quizzy.mrk.leagueoflegends.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

    private String platformId;
    private int gameId;
    private int champion;
    private int queue;
    private int season;
    private int timestamp;
    private String role;
    private String lane;

    public Game(String platformId, int gameId, int champion, int queue, int season, int timestamp, String role, String lane) {
        this.platformId = platformId;
        this.gameId = gameId;
        this.champion = champion;
        this.queue = queue;
        this.season = season;
        this.timestamp = timestamp;
        this.role = role;
        this.lane = lane;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.platformId);
        dest.writeInt(this.gameId);
        dest.writeInt(this.champion);
        dest.writeInt(this.queue);
        dest.writeInt(this.season);
        dest.writeInt(this.timestamp);
        dest.writeString(this.role);
        dest.writeString(this.lane);
    }

    public Game(Parcel in){
        this.platformId = in.readString();
        this.gameId = in.readInt();
        this.champion = in.readInt();
        this.queue = in.readInt();
        this.season = in.readInt();
        this.timestamp = in.readInt();
        this.role = in.readString();
        this.lane = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {

        public Game createFromParcel(Parcel source){
            return new Game(source);
        }

        public Game[] newArray(int size){
            return new Game[size];
        }
    };
}
