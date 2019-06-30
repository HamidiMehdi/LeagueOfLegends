package com.quizzy.mrk.leagueoflegends.Application;

public class ApiRequest {

    public static String API_KEY = "RGAPI-e11cfdda-9a8e-460e-babf-695489cd58e5";
    public static String URL = "https://%location%.api.riotgames.com/";

    public static String getUrl(String region) {
        return ApiRequest.URL.replace("%location%",region);
    }
}
