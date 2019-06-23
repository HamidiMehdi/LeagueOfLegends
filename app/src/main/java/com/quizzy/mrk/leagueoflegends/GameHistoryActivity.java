package com.quizzy.mrk.leagueoflegends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.Player;

public class GameHistoryActivity extends AppCompatActivity {

    private Player player;
    private TextView tvPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        this.tvPlayer = findViewById(R.id.test);
        this.player = getIntent().getExtras().getParcelable("player");

        this.tvPlayer.setText(this.player.toString());
    }
}
