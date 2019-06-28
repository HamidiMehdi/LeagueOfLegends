package com.quizzy.mrk.leagueoflegends;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.Champion;
import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.Spell;
import com.quizzy.mrk.leagueoflegends.Requests.LastGames;
import com.quizzy.mrk.leagueoflegends.Services.ChampionService;
import com.quizzy.mrk.leagueoflegends.Services.SessionService;
import com.quizzy.mrk.leagueoflegends.Services.SpellService;

import java.util.ArrayList;

public class GameHistoryActivity extends AppCompatActivity {

    private TextView tvPlayer;
    private ArrayList<Game> games;

    private LastGames lastGamesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        this.tvPlayer = findViewById(R.id.test);

        this.tvPlayer.setText(SessionService.getSession().getPlayer().toString());

        this.lastGamesRequest = new LastGames(this);

        this.getLastGames();
    }

    private void getLastGames() {
        this.lastGamesRequest.getTwentyLastGamesRequest(SessionService.getSession().getPlayer(), new LastGames.LastGamesCallback() {
            @Override
            public void onSuccess(ArrayList<Game> listGames) {
                games = listGames;
                getGamesStat();
            }

            @Override
            public void onErrorNetwork() {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.activity_game_history), R.string.error_network, 2500);
                snackbar.show();
            }

            @Override
            public void onErrorVolley() {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.activity_game_history), R.string.error_volley, 2500);
                snackbar.show();
            }
        });
    }

    private void getGamesStat() {
        Champion champ = ChampionService.getChampionService().getChampion(58);
        Log.d("APP", champ.toString());

        Spell spell = SpellService.getSpellService().getSpell(12);
        Log.d("APP", spell.toString());
    }
}
