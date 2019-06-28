package com.quizzy.mrk.leagueoflegends;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Requests.GameStatRequest;
import com.quizzy.mrk.leagueoflegends.Requests.LastGames;
import com.quizzy.mrk.leagueoflegends.Services.SessionService;
import com.squareup.picasso.Picasso;

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
        HiostoryGamesAdapter adapter = new HiostoryGamesAdapter(GameHistoryActivity.this);
        this.lvHistoryGames.setAdapter(adapter);
    }



    class HiostoryGamesAdapter extends ArrayAdapter<GameStat> {
        LayoutInflater inflater;

        public HiostoryGamesAdapter(Activity context) {
            super(context, R.layout.adapter_history_game, gamesStat);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View vItem = convertView;
            if (vItem == null) {
                vItem = getLayoutInflater().inflate(R.layout.adapter_history_game, parent, false);
            }

            ImageView ivChampion = vItem.findViewById(R.id.iv_champion);
            Picasso.with(getContext()).load(gamesStat.get(position).getGame().getChampion().getUrlImageChampion()).into(ivChampion);

            return vItem;
        }
    }

}
