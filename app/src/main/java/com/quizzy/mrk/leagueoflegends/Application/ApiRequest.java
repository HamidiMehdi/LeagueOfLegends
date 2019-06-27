package com.quizzy.mrk.leagueoflegends.Application;

public class ApiRequest {

    public static String API_KEY = "RGAPI-2b7be79c-7e80-41a9-b8fd-2b4c789f25bd";
    public static String URL = "https://%location%.api.riotgames.com/";

    public static String getUrl(String region) {
        return ApiRequest.URL.replace("%location%",region);
    }
}
