package com.quizzy.mrk.leagueoflegends.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.quizzy.mrk.leagueoflegends.Application.VolleySingleton;
import com.quizzy.mrk.leagueoflegends.Entities.Champion;
import com.quizzy.mrk.leagueoflegends.Services.ChampionService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchChampions {

    private RequestQueue queue;

    public SearchChampions(Context context) {
        this.queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void getAllChampions(final ChampionCallback callBack) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("APP", "champion recupéré ==> " + response.toString());
                        ArrayList<Champion> listChampion = new ArrayList<>();

                        try {
                            JSONObject champions = response.getJSONObject("data");
                            Iterator keys = champions.keys();
                            while(keys.hasNext()) {
                                String key = (String) keys.next();
                                JSONObject champion = champions.getJSONObject(key);
                                listChampion.add(
                                  new Champion(
                                          champion.getString("id"),
                                          Integer.parseInt(champion.getString("key")),
                                          champion.getString("name"),
                                          champion.getString("title"),
                                          champion.getString("blurb"),
                                          champion.getJSONObject("image").getString("full")
                                  )
                                );
                            }
                            ChampionService.open(listChampion);
                            callBack.onSuccess();
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

    public interface ChampionCallback {
        void onSuccess();
        void onErrorNetwork();
        void onErrorVolley();
    }
}

