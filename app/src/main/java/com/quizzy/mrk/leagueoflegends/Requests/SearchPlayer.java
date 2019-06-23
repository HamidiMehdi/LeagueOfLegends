package com.quizzy.mrk.leagueoflegends.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.quizzy.mrk.leagueoflegends.Application.ApiRequest;
import com.quizzy.mrk.leagueoflegends.Application.VolleySingleton;
import com.quizzy.mrk.leagueoflegends.Entities.Player;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchPlayer {

    private Context context;
    private RequestQueue queue;

    public SearchPlayer(Context context) {
        this.context = context;
        this.queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void searchPlayerRequest(final String region, final String namePlayer, final SearchPlayerCallback callBack) {
        Log.d("APP", ApiRequest.getUrl(region) + "lol/summoner/v4/summoners/by-name/" + namePlayer + "?api_key=" + ApiRequest.API_KEY);
         JsonObjectRequest request = new JsonObjectRequest(
                 Request.Method.GET,
                 ApiRequest.getUrl(region) + "lol/summoner/v4/summoners/by-name/" + namePlayer + "?api_key=" + ApiRequest.API_KEY,
                 new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                         Log.d("APP", "Player recupéré ==> " + response.toString());

                         try {
                            Player player = new Player(
                                    response.getString("id"),
                                    response.getString("accountId"),
                                    response.getString("puuid"),
                                    response.getString("name"),
                                    ApiRequest.getUrl(region),
                                    response.getInt("profileIconId"),
                                    response.getInt("revisionDate"),
                                    response.getInt("summonerLevel")
                            );
                            callBack.onSuccess(player);
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         if (error instanceof ServerError) {
                             callBack.dontExist();
                         } else if (error instanceof VolleyError) {
                             callBack.onErrorVollet();
                         } else if (error instanceof NetworkError) {
                             callBack.onErrorNetwork();
                         }
                     }
                 }
         );
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(request);
    }

    public interface SearchPlayerCallback {
        void onSuccess(Player player);
        void dontExist();
        void onErrorNetwork();
        void onErrorVollet();
    }
}
