package com.quizzy.mrk.leagueoflegends.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.R;

public class GameFragment extends Fragment {

    private GameStat gameStat;

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
        View fragment = inflater.inflate(R.layout.fragment_game, container, false);
        this.gameStat = (GameStat) getArguments().getParcelable("gameStat");

        return fragment;
    }
}
