package com.quizzy.mrk.leagueoflegends.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.R;
import com.squareup.picasso.Picasso;

public class GameFragment extends Fragment {

    private GameStat gameStat;

    private ImageView ivWinner1;
    private ImageView ivWinner2;
    private ImageView ivWinner3;
    private ImageView ivWinner4;
    private ImageView ivWinner5;
    private ImageView ivLoser1;
    private ImageView ivLoser2;
    private ImageView ivLoser3;
    private ImageView ivLoser4;
    private ImageView ivLoser5;

    public static GameFragment newInstance(GameStat gameStatB) {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("gameStat", gameStatB);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        this.gameStat = (GameStat) getArguments().getParcelable("gameStat");

        this.ivWinner1 = view.findViewById(R.id.iv_winner1);
        this.ivWinner2 = view.findViewById(R.id.iv_winner2);
        this.ivWinner3 = view.findViewById(R.id.iv_winner3);
        this.ivWinner4 = view.findViewById(R.id.iv_winner4);
        this.ivWinner5 = view.findViewById(R.id.iv_winner5);
        this.ivLoser1 = view.findViewById(R.id.iv_loser1);
        this.ivLoser2 = view.findViewById(R.id.iv_loser2);
        this.ivLoser3 = view.findViewById(R.id.iv_loser3);
        this.ivLoser4 = view.findViewById(R.id.iv_loser4);
        this.ivLoser5 = view.findViewById(R.id.iv_loser5);

        this.hydrateView();

        return view;
    }

    private void hydrateView() {
        Picasso.with(getContext()).load(gameStat.getTeamWin().get(0).getUrlImageChampion()).into(this.ivWinner1);
        Picasso.with(getContext()).load(gameStat.getTeamWin().get(1).getUrlImageChampion()).into(this.ivWinner2);
        Picasso.with(getContext()).load(gameStat.getTeamWin().get(2).getUrlImageChampion()).into(this.ivWinner3);
        Picasso.with(getContext()).load(gameStat.getTeamWin().get(3).getUrlImageChampion()).into(this.ivWinner4);
        Picasso.with(getContext()).load(gameStat.getTeamWin().get(4).getUrlImageChampion()).into(this.ivWinner5);

        Picasso.with(getContext()).load(gameStat.getTeamLose().get(0).getUrlImageChampion()).into(this.ivLoser1);
        Picasso.with(getContext()).load(gameStat.getTeamLose().get(1).getUrlImageChampion()).into(this.ivLoser2);
        Picasso.with(getContext()).load(gameStat.getTeamLose().get(2).getUrlImageChampion()).into(this.ivLoser3);
        Picasso.with(getContext()).load(gameStat.getTeamLose().get(3).getUrlImageChampion()).into(this.ivLoser4);
        Picasso.with(getContext()).load(gameStat.getTeamLose().get(4).getUrlImageChampion()).into(this.ivLoser5);
    }
}
