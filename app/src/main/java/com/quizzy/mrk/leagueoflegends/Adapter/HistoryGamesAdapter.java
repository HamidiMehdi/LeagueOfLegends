package com.quizzy.mrk.leagueoflegends.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.GameStatActivity;
import com.quizzy.mrk.leagueoflegends.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HistoryGamesAdapter extends ArrayAdapter<GameStat> {

    private LayoutInflater inflater;
    private ArrayList<GameStat> gamesStat;
    private Activity context;

    public HistoryGamesAdapter(Activity context, ArrayList<GameStat> gameStats) {
        super(context, R.layout.adapter_history_game, gameStats);
        this.inflater = LayoutInflater.from(context);
        this.gamesStat = gameStats;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vItem = convertView;
        if (vItem == null) {
            vItem = context.getLayoutInflater().inflate(R.layout.adapter_history_game, parent, false);
        }

        GameStat gameStat = gamesStat.get(position);

        View view = vItem.findViewById(R.id.win_or_lose);
        CardView cv = vItem.findViewById(R.id.cardview);

        if (gameStat.isWin()) {
            view.setBackgroundColor(context.getResources().getColor(R.color.green));
            cv.setBackgroundColor(context.getResources().getColor(R.color.win_row_bg));
        } else {
            view.setBackgroundColor(context.getResources().getColor(R.color.red));
            cv.setBackgroundColor(context.getResources().getColor(R.color.lose_row_bg));
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

        vItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameStatActivity.class);
                context.startActivity(intent);
            }
        });

        return vItem;
    }
}