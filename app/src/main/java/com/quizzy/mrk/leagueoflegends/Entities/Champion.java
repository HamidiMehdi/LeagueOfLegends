package com.quizzy.mrk.leagueoflegends.Entities;

public class Champion {

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
}
