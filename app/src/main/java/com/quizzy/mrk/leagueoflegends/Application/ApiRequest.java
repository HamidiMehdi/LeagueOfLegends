package com.quizzy.mrk.leagueoflegends.Application;

public class ApiRequest {

    public static String API_KEY = "RGAPI-47dead36-053c-486f-92f8-75ce21c8bb91";
    public static String URL = "https://%location%.api.riotgames.com/";

    public static String getUrl(String region) {
        return ApiRequest.URL.replace("%location%",region);
    }
}
