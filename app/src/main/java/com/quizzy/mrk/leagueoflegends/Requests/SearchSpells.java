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
import com.quizzy.mrk.leagueoflegends.Entities.Spell;
import com.quizzy.mrk.leagueoflegends.Services.SpellService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchSpells {

    private RequestQueue queue;

    public SearchSpells(Context context) {
        this.queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void getAllSpells(final SpellCallback callBack) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/summoner.json",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("APP", "champion recupéré ==> " + response.toString());
                        ArrayList<Spell> listSpell = new ArrayList<>();

                        try {
                            JSONObject spells = response.getJSONObject("data");
                            Iterator keys = spells.keys();
                            while(keys.hasNext()) {
                                String key = (String) keys.next();
                                JSONObject spell = spells.getJSONObject(key);
                                listSpell.add(
                                        new Spell(
                                                spell.getString("id"),
                                                spell.getString("name"),
                                                spell.getString("description"),
                                                Integer.parseInt(spell.getString("key")),
                                                spell.getJSONObject("image").getString("full")
                                        )
                                );
                            }
                            SpellService.open(listSpell);
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

    public interface SpellCallback {
        void onSuccess();
        void onErrorNetwork();
        void onErrorVolley();
    }
}

