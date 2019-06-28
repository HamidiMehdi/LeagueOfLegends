package com.quizzy.mrk.leagueoflegends.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private String region;
    private int profileIconId;
    private int revisionDate;
    private int summonerLevel;

    public Player(String id, String accountId, String puuid, String name, String region, int profileIconId, int revisionDate, int summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.region = region;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public int getRevisionDate() {
        return revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public String getUrlProfilIcon() {
        return "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + this.profileIconId + ".png";
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", profileIconId=" + profileIconId +
                ", revisionDate=" + revisionDate +
                ", summonerLevel=" + summonerLevel +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.accountId);
        dest.writeString(this.puuid);
        dest.writeString(this.name);
        dest.writeString(this.region);
        dest.writeInt(this.profileIconId);
        dest.writeInt(this.revisionDate);
        dest.writeInt(this.summonerLevel);
    }

    public Player(Parcel in){
        this.id = in.readString();
        this.accountId = in.readString();
        this.puuid = in.readString();
        this.name = in.readString();
        this.region = in.readString();
        this.profileIconId = in.readInt();
        this.revisionDate = in.readInt();
        this.summonerLevel = in.readInt();

    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {

        public Player createFromParcel(Parcel source){
            return new Player(source);
        }

        public Player[] newArray(int size){
            return new Player[size];
        }
    };
}
