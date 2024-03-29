package com.quizzy.mrk.leagueoflegends.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.quizzy.mrk.leagueoflegends.Application.ApiRequest;
import com.quizzy.mrk.leagueoflegends.Application.VolleySingleton;
import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.Player;
import com.quizzy.mrk.leagueoflegends.Services.ChampionService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LastGames {

    private RequestQueue queue;

    public LastGames(Context context) {
        this.queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void getTwentyLastGamesRequest(final Player player, final LastGamesCallback callBack) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                ApiRequest.getUrl(player.getRegion()) + "lol/match/v4/matchlists/by-account/" + player.getAccountId() + "?api_key=" + ApiRequest.API_KEY + "&endIndex=20&beginIndex=0",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("APP", "games recupérées ==> " + response.toString());
                        ArrayList<Game> listGames = new ArrayList<>();

                        try {
                            JSONArray games = response.getJSONArray("matches");
                            for (int i = 0; i < games.length(); i++) {
                                JSONObject game = (JSONObject) games.get(i);
                                listGames.add(
                                        new Game(
                                                game.getString("platformId"),
                                                game.getLong("gameId"),
                                                ChampionService.getChampionService().getChampion(game.getInt("champion")),
                                                game.getInt("queue"),
                                                game.getInt("season"),
                                                game.getLong("timestamp"),
                                                game.getString("role"),
                                                game.getString("lane")
                                        )
                                );
                            }

                            callBack.onSuccess(sortGames(listGames));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof VolleyError) {
                            callBack.onErrorVolley();
                        } else if (error instanceof NetworkError) {
                            callBack.onErrorNetwork();
                        }
                    }
                }
        );
        queue.add(request);
    }

    private ArrayList<Game> sortGames(ArrayList<Game> games) {
        ArrayList<Game> sortGames = new ArrayList<>();

        while (games.size() != 0) {
            int index = 0;
            long timestamp = 0;
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getTimestamp() > timestamp) {
                    timestamp = games.get(i).getTimestamp();
                    index = i;
                }
            }
            sortGames.add(games.get(index));
            games.remove(index);
            timestamp = 0;
        }

        return sortGames;
    }

    public interface LastGamesCallback {
        void onSuccess(ArrayList<Game> listGames);

        void onErrorNetwork();

        void onErrorVolley();
    }
}

