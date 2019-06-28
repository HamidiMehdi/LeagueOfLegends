package com.quizzy.mrk.leagueoflegends.Services;

import com.quizzy.mrk.leagueoflegends.Entities.Champion;

import java.util.ArrayList;

public class ChampionService {

    private static ChampionService championService = null;
    private ArrayList<Champion> champions = null;

    private ChampionService(ArrayList<Champion> champions) {
        this.champions = champions;
    }

    public static void open(ArrayList<Champion> champions) {
        ChampionService.championService = new ChampionService(champions);
    }

    public Champion getChampion(int key) {
        Champion champ = null;
        for (Champion champion : this.champions) {
            if (champion.getKey() == key) {
                champ = champion;
                break;
            }
        }

        return champ;
    }

    public static ChampionService getChampionService() {
        return ChampionService.championService;
    }
}