package com.quizzy.mrk.leagueoflegends;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.quizzy.mrk.leagueoflegends.Adapter.HistoryGamesAdapter;
import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Requests.GameStatRequest;
import com.quizzy.mrk.leagueoflegends.Requests.LastGames;
import com.quizzy.mrk.leagueoflegends.Services.SessionService;

import java.util.ArrayList;

public class GameHistoryActivity extends AppCompatActivity {

    private ListView lvHistoryGames;
    private ArrayList<Game> games;
    private ArrayList<GameStat> gamesStat = new ArrayList<>();
    private LastGames lastGamesRequest;
    private GameStatRequest gameStatRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        this.lastGamesRequest = new LastGames(this);
        this.gameStatRequest = new GameStatRequest(this);
        this.lvHistoryGames = findViewById(R.id.lv_history_games);

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
        for (Game game : this.games) {
            this.gameStatRequest.getGameStatRequest(SessionService.getSession().getPlayer(), game, new GameStatRequest.GameStatCallback() {
                @Override
                public void onSuccess(GameStat gameStat) {
                    gamesStat.add(gameStat);
                    updateHistoryList();
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
    }

    private void updateHistoryList() {
        HistoryGamesAdapter adapter = new HistoryGamesAdapter(GameHistoryActivity.this, this.gamesStat);
        this.lvHistoryGames.setAdapter(adapter);
    }
}










