package com.quizzy.mrk.leagueoflegends.Application;

public class ApiRequest {

    public static String API_KEY = "RGAPI-18c290a4-1dc8-4d14-a43e-958dcbe1340b";
    public static String URL = "https://%location%.api.riotgames.com/";

    public static String getUrl(String region) {
        return ApiRequest.URL.replace("%location%",region);
    }
}
