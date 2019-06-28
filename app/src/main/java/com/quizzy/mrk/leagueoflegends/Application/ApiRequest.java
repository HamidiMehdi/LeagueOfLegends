package com.quizzy.mrk.leagueoflegends.Application;

public class ApiRequest {

    public static String API_KEY = "RGAPI-a3cad906-1fc1-4026-94c2-7027ea02efc0";
    public static String URL = "https://%location%.api.riotgames.com/";

    public static String getUrl(String region) {
        return ApiRequest.URL.replace("%location%",region);
    }
}
