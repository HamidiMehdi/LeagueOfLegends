package com.quizzy.mrk.leagueoflegends;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.Game;
import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Requests.GameStatRequest;
import com.quizzy.mrk.leagueoflegends.Requests.LastGames;
import com.quizzy.mrk.leagueoflegends.Services.SessionService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            Log.d("APP", "" + game.getTimestamp());

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
        HistoryGamesAdapter adapter = new HistoryGamesAdapter(GameHistoryActivity.this);
        this.lvHistoryGames.setAdapter(adapter);
    }


    class HistoryGamesAdapter extends ArrayAdapter<GameStat> {
        LayoutInflater inflater;

        public HistoryGamesAdapter(Activity context) {
            super(context, R.layout.adapter_history_game, gamesStat);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View vItem = convertView;
            if (vItem == null) {
                vItem = getLayoutInflater().inflate(R.layout.adapter_history_game, parent, false);
            }

            GameStat gameStat = gamesStat.get(position);

            View view = vItem.findViewById(R.id.win_or_lose);
            CardView cv = vItem.findViewById(R.id.cardview);

            if (gameStat.isWin()) {
                view.setBackgroundColor(getResources().getColor(R.color.green));
                cv.setBackgroundColor(getResources().getColor(R.color.win_row_bg));
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                cv.setBackgroundColor(getResources().getColor(R.color.lose_row_bg));
            }

            ImageView ivChampion = vItem.findViewById(R.id.iv_portrait);
            Picasso.with(getContext()).load(gameStat.getGame().getChampion().getUrlImageChampion()).into(ivChampion);

            TextView tvTypeMatch = vItem.findViewById(R.id.tv_type_match);
            tvTypeMatch.setText(gameStat.getGameMode());

            TextView tvKda = vItem.findViewById(R.id.tv_kda);
            tvKda.setText(gameStat.getKill() + "/" + gameStat.getDeath() + "/" + gameStat.getAssist());

            TextView tvGold = vItem.findViewById(R.id.tv_gold);
            tvGold.setText(Math.round(gameStat.getGold() / 1000.0) + "K");

            TextView tvCs = vItem.findViewById(R.id.tv_cs);
            tvCs.setText(gameStat.getCs() + "");

            ImageView item1 = vItem.findViewById(R.id.iv_item1);
            Picasso.with(getContext()).load(gameStat.getItems().get(0)).into(item1);

            ImageView item2 = vItem.findViewById(R.id.iv_item2);
            Picasso.with(getContext()).load(gameStat.getItems().get(1)).into(item2);

            ImageView item3 = vItem.findViewById(R.id.iv_item3);
            Picasso.with(getContext()).load(gameStat.getItems().get(2)).into(item3);

            ImageView item4 = vItem.findViewById(R.id.iv_item4);
            Picasso.with(getContext()).load(gameStat.getItems().get(3)).into(item4);

            ImageView item5 = vItem.findViewById(R.id.iv_item5);
            Picasso.with(getContext()).load(gameStat.getItems().get(4)).into(item5);

            ImageView item6 = vItem.findViewById(R.id.iv_item6);
            Picasso.with(getContext()).load(gameStat.getItems().get(5)).into(item6);

            ImageView item7 = vItem.findViewById(R.id.iv_item7);
            Picasso.with(getContext()).load(gameStat.getItems().get(6)).into(item7);

            TextView tvDuration = vItem.findViewById(R.id.tv_duration);
            int hour = (int) Math.floor(gameStat.getGameDuration() / 3600);
            int minute = (int) Math.floor((gameStat.getGameDuration() / 60) % 60);
            int second = (int) Math.floor(gameStat.getGameDuration() % 60);
            tvDuration.setText((hour == 0 ? "" : hour + ":") + minute + ":" + second);

            TextView tvDate = vItem.findViewById(R.id.tv_date);
            GregorianCalendar date = new GregorianCalendar();
            date.setTimeInMillis(gameStat.getGame().getTimestamp());
            int day = date.get(Calendar.DAY_OF_MONTH);
            int month = date.get(Calendar.MONTH);
            int year = date.get(Calendar.YEAR);
            tvDate.setText(day + "/" + month + "/" + year);

            return vItem;
        }
    }

}
