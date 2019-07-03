package com.quizzy.mrk.leagueoflegends.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Champion implements Parcelable {

    private String id;
    private int key;
    private String name;
    private String title;
    private String blurb;
    private String img;

    public Champion(String id, int key, String name, String title, String blurb, String img) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrlImageChampion() {
        return "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + this.img;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "id='" + id + '\'' +
                ", key=" + key +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", blurb='" + blurb + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.key);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.blurb);
        dest.writeString(this.img);
    }

    public Champion(Parcel in) {
        this.id = in.readString();
        this.key = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
        this.blurb = in.readString();
        this.img = in.readString();
    }

    public static final Creator<Champion> CREATOR = new Creator<Champion>() {

        public Champion createFromParcel(Parcel source) {
            return new Champion(source);
        }

        public Champion[] newArray(int size) {
            return new Champion[size];
        }
    };
}
