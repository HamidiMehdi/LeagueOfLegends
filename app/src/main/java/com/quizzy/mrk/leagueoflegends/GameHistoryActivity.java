package com.quizzy.mrk.leagueoflegends;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.Player;
import com.quizzy.mrk.leagueoflegends.Requests.LastGames;

import java.util.ArrayList;

public class GameHistoryActivity extends AppCompatActivity {

    private Player player;
    private TextView tvPlayer;
    private ArrayList<Game> games;

    private LastGames lastGamesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        this.tvPlayer = findViewById(R.id.test);
        this.player = getIntent().getExtras().getParcelable("player");

        this.tvPlayer.setText(this.player.toString());

        this.lastGamesRequest = new LastGames(this);


        this.getLastGames();
    }

    private void getLastGames() {
        this.lastGamesRequest.getTwentyLastGamesRequest(this.player, new LastGames.LastGamesCallback() {
            @Override
            public void onSuccess(ArrayList<Game> listGames) {
                games = listGames;
                Log.d("APP", "nb => " + games.size());
            }

            @Override
            public void onErrorNetwork() {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.activity_game_history), R.string.error_network, 2500);
                snackbar.show();
            }

            @Override
            public void onErrorVollet() {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.activity_game_history), R.string.error_volley, 2500);
                snackbar.show();
            }
        });
    }
}
