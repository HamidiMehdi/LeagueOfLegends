package com.quizzy.mrk.leagueoflegends.Services;

import com.quizzy.mrk.leagueoflegends.Entities.Player;

public class SessionService {

    private static SessionService session = null ;
    private Player player = null;

    private SessionService (Player player){
        this.player = player;
    }

    public static void open(Player player){
        SessionService.session = new SessionService(player);
    }

    public static void close(){
        SessionService.session = null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public static SessionService getSession() {
        return SessionService.session;
    }
}
