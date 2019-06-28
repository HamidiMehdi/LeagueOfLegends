package com.quizzy.mrk.leagueoflegends.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {

    private String platformId;
    private long gameId;
    private Champion champion;
    private int queue;
    private int season;
    private int timestamp;
    private String role;
    private String lane;

    public Game(String platformId, long gameId, Champion champion, int queue, int season, int timestamp, String role, String lane) {
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

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
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
    public String toString() {
        return "Game{" +
                "platformId='" + platformId + '\'' +
                ", gameId=" + gameId +
                ", champion=" + champion +
                ", queue=" + queue +
                ", season=" + season +
                ", timestamp=" + timestamp +
                ", role='" + role + '\'' +
                ", lane='" + lane + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.platformId);
        dest.writeLong(this.gameId);
        dest.writeValue(this.champion);
        dest.writeInt(this.queue);
        dest.writeInt(this.season);
        dest.writeInt(this.timestamp);
        dest.writeString(this.role);
        dest.writeString(this.lane);
    }

    public Game(Parcel in){
        this.platformId = in.readString();
        this.gameId = in.readLong();
        this.champion = (Champion) in.readValue(Champion.class.getClassLoader());
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
