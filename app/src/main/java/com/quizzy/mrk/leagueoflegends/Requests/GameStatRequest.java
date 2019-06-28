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
import com.quizzy.mrk.leagueoflegends.Entities.Champion;
import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Entities.Player;
import com.quizzy.mrk.leagueoflegends.Entities.Spell;
import com.quizzy.mrk.leagueoflegends.Services.ChampionService;
import com.quizzy.mrk.leagueoflegends.Services.SpellService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameStatRequest {

    private RequestQueue queue;

    public GameStatRequest(Context context) {
        this.queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void getGameStatRequest(final Player player, final Game game, final GameStatCallback callBack) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                ApiRequest.getUrl(player.getRegion()) + "lol/match/v4/matches/" + game.getGameId() + "?api_key=" + ApiRequest.API_KEY,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int participantId = getParticipantId(player, response.getJSONArray("participantIdentities"));
                            int champLevel = 0, gold = 0, kill = 0, death = 0, assist = 0;
                            boolean win = false;
                            ArrayList<Spell> spells = new ArrayList<>();
                            ArrayList<String> items = new ArrayList<>();
                            ArrayList<Champion> teamWin = new ArrayList<>();
                            ArrayList<Champion> teamLose = new ArrayList<>();

                            int teamWinId = 0;
                            int teamLoseId = 0;
                            JSONArray teams = response.getJSONArray("teams");
                            for (int i = 0; i < teams.length(); i++) {
                                JSONObject team = teams.getJSONObject(i);
                                if (team.getString("win").equals("Win")) {
                                    teamWinId = team.getInt("teamId");
                                } else {
                                    teamLoseId = team.getInt("teamId");
                                }
                            }

                            JSONArray participants = response.getJSONArray("participants");
                            for (int i = 0; i < participants.length(); i++) {
                                JSONObject participant = participants.getJSONObject(i);

                                if (participant.getInt("teamId") == teamWinId) {
                                    teamWin.add(ChampionService.getChampionService().getChampion(participant.getInt("championId")));
                                } else {
                                    teamLose.add(ChampionService.getChampionService().getChampion(participant.getInt("championId")));
                                }

                                if (participant.getInt("participantId") == participantId) {
                                    JSONObject stat = participant.getJSONObject("stats");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item0") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item1") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item2") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item3") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item4") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item5") + ".png");
                                    items.add("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + stat.getInt("item6") + ".png");

                                    spells.add(SpellService.getSpellService().getSpell(participant.getInt("spell1Id")));
                                    spells.add(SpellService.getSpellService().getSpell(participant.getInt("spell2Id")));

                                    win = stat.getBoolean("win");
                                    champLevel = stat.getInt("champLevel");
                                    gold = stat.getInt("goldEarned");
                                    kill = stat.getInt("kills");
                                    death = stat.getInt("deaths");
                                    assist = stat.getInt("assists");
                                }
                            }

                            GameStat gameStat = new GameStat(
                                    game,
                                    response.getInt("gameDuration"),
                                    response.getString("gameMode"),
                                    champLevel,
                                    gold,
                                    kill,
                                    death,
                                    assist,
                                    win,
                                    spells,
                                    items,
                                    teamWin,
                                    teamLose
                            );

                            Log.d("APP", gameStat.toString());

                            callBack.onSuccess(gameStat);
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
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(request);
    }

    /**
     * @param player
     * @param json
     * @return
     * @throws JSONException
     */
    private int getParticipantId(Player player, JSONArray json) throws JSONException {
        int participantId = 0;
        for (int i = 0; i < json.length(); i++) {
            JSONObject participant = json.getJSONObject(i);
            if (participant.getJSONObject("player").getString("accountId") == player.getAccountId()) {
                participantId = participant.getInt("participantId");
                break;
            }
        }

        return participantId;
    }

    public interface GameStatCallback {
        void onSuccess(GameStat gameStat);

        void onErrorNetwork();

        void onErrorVolley();
    }
}

