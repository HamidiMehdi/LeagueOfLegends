package com.quizzy.mrk.leagueoflegends.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Spell implements Parcelable {

    private String id;
    private String name;
    private String description;
    private int key;
    private String img;

    public Spell(String id, String name, String description, int key, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrlImageSpell() {
        return "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/" + this.img;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", key=" + key +
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
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.key);
        dest.writeString(this.img);
    }

    public Spell(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.key = in.readInt();
        this.img = in.readString();
    }

    public static final Creator<Spell> CREATOR = new Creator<Spell>() {

        public Spell createFromParcel(Parcel source){
            return new Spell(source);
        }

        public Spell[] newArray(int size){
            return new Spell[size];
        }
    };
}
