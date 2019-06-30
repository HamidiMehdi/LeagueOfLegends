package com.quizzy.mrk.leagueoflegends.Entities;

import org.json.JSONObject;

import java.util.ArrayList;

public class GameStat {

    private Game game;
    private long gameDuration;
    private String gameMode;
    private int ChampLevel;
    private int gold;
    private int cs;
    private int kill;
    private int death;
    private int assist;
    private boolean win;
    private ArrayList<Spell> spells;
    private ArrayList<String> items;
    private ArrayList<Champion> teamWin;
    private ArrayList<Champion> teamLose;

    public GameStat(Game game, long gameDuration, String gameMode, int champLevel, int gold, int cs,
                    int kill, int death, int assist, boolean win, ArrayList<Spell> spells,
                    ArrayList<String> items, ArrayList<Champion> teamWin, ArrayList<Champion> teamLose) {
        this.game = game;
        this.gameDuration = gameDuration;
        this.gameMode = gameMode;
        this.ChampLevel = champLevel;
        this.gold = gold;
        this.cs = cs;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
        this.win = win;
        this.spells = spells;
        this.items = items;
        this.teamWin = teamWin;
        this.teamLose = teamLose;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getChampLevel() {
        return ChampLevel;
    }

    public void setChampLevel(int champLevel) {
        ChampLevel = champLevel;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public ArrayList<Champion> getTeamWin() {
        return teamWin;
    }

    public void setTeamWin(ArrayList<Champion> teamWin) {
        this.teamWin = teamWin;
    }

    public ArrayList<Champion> getTeamLose() {
        return teamLose;
    }

    public void setTeamLose(ArrayList<Champion> teamLose) {
        this.teamLose = teamLose;
    }

    @Override
    public String toString() {
        return "GameStat{" +
                "game=" + game +
                ", gameDuration=" + gameDuration +
                ", gameMode='" + gameMode + '\'' +
                ", ChampLevel=" + ChampLevel +
                ", gold=" + gold +
                ", kill=" + kill +
                ", death=" + death +
                ", assist=" + assist +
                ", win=" + win +
                ", spells=" + spells +
                ", items=" + items +
                ", teamWin=" + teamWin +
                ", teamLose=" + teamLose +
                '}';
    }
}
