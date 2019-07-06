package com.quizzy.mrk.leagueoflegends;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Fragment.GameFragment;
import com.quizzy.mrk.leagueoflegends.Fragment.StatFragment;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GameStatActivity extends AppCompatActivity {

    private GameStat gameStat;

    private RelativeLayout rvInfo;
    private ImageView ivChamp;
    private ImageView ivSum1;
    private ImageView ivSum2;
    private TextView tvTypeMatch;
    private TextView tvLvlChamp;
    private TextView tvGold;
    private TextView tvCs;
    private TextView tvKda;
    private ImageView ivItem1;
    private ImageView ivItem2;
    private ImageView ivItem3;
    private ImageView ivItem4;
    private ImageView ivItem5;
    private ImageView ivItem6;
    private ImageView ivItem7;
    private TextView tvDuration;
    private TextView tvDate;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stat);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.game));
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.bottomNav = findViewById(R.id.bottom_nav);
        this.bottomNav.setOnNavigationItemSelectedListener(navListener);
        this.bottomNav.setSelectedItemId(R.id.game); // initial

        this.rvInfo = findViewById(R.id.rl_infos_joueur);
        this.ivChamp = findViewById(R.id.iv_details_portrait);
        this.ivSum1 = findViewById(R.id.iv_details_sum1);
        this.ivSum2 = findViewById(R.id.iv_details_sum2);
        this.tvTypeMatch = findViewById(R.id.tv_details_type_match);
        this.tvLvlChamp = findViewById(R.id.tv_details_level);
        this.tvGold = findViewById(R.id.tv_details_gold);
        this.tvCs = findViewById(R.id.tv_details_cs);
        this.tvKda = findViewById(R.id.tv_details_kda);
        this.ivItem1 = findViewById(R.id.iv_details_item1);
        this.ivItem2 = findViewById(R.id.iv_details_item2);
        this.ivItem3 = findViewById(R.id.iv_details_item3);
        this.ivItem4 = findViewById(R.id.iv_details_item4);
        this.ivItem5 = findViewById(R.id.iv_details_item5);
        this.ivItem6 = findViewById(R.id.iv_details_item6);
        this.ivItem7 = findViewById(R.id.iv_details_item7);
        this.tvDuration = findViewById(R.id.tv_details_duration);
        this.tvDate = findViewById(R.id.tv_details_creation);

        this.gameStat = getIntent().getExtras().getParcelable("gameStat");

        this.hydrateView();
    }

    private void hydrateView() {
        if (!gameStat.isWin()) {
            this.rvInfo.setBackgroundColor(getResources().getColor(R.color.lose_row_bg));
        }

        Picasso.with(this).load(this.gameStat.getGame().getChampion().getUrlImageChampion()).into(this.ivChamp);
        Picasso.with(this).load(this.gameStat.getSpells().get(0).getUrlImageSpell()).into(this.ivSum1);
        Picasso.with(this).load(this.gameStat.getSpells().get(1).getUrlImageSpell()).into(this.ivSum2);

        this.tvTypeMatch.setText(gameStat.getGameMode());
        this.tvLvlChamp.setText(getResources().getString(R.string.lvl_champ) + " " + gameStat.getChampLevel());

        this.tvGold.setText(Math.round(gameStat.getGold() / 1000.0) + "K");
        this.tvCs.setText(gameStat.getCs() + "");
        this.tvKda.setText(gameStat.getKill() + "/" + gameStat.getDeath() + "/" + gameStat.getAssist());

        Picasso.with(this).load(gameStat.getItems().get(0)).into(this.ivItem1);
        Picasso.with(this).load(gameStat.getItems().get(1)).into(this.ivItem2);
        Picasso.with(this).load(gameStat.getItems().get(2)).into(this.ivItem3);
        Picasso.with(this).load(gameStat.getItems().get(3)).into(this.ivItem4);
        Picasso.with(this).load(gameStat.getItems().get(4)).into(this.ivItem5);
        Picasso.with(this).load(gameStat.getItems().get(5)).into(this.ivItem6);
        Picasso.with(this).load(gameStat.getItems().get(6)).into(this.ivItem7);

        int hour = (int) Math.floor(gameStat.getGameDuration() / 3600);
        int minute = (int) Math.floor((gameStat.getGameDuration() / 60) % 60);
        int second = (int) Math.floor(gameStat.getGameDuration() % 60);
        this.tvDuration.setText((hour == 0 ? "" : hour + ":") + minute + ":" + second);

        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(gameStat.getGame().getTimestamp());
        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH);
        int year = date.get(Calendar.YEAR);
        this.tvDate.setText(day + "/" + month + "/" + year);
    }

    /**
     * Listener navigation bottom
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            gameStat = getIntent().getExtras().getParcelable("gameStat");

            switch (item.getItemId()) {
                case R.id.game :
                    fragment = GameFragment.newInstance(gameStat);
                    break;
                case R.id.stat :
                    fragment = StatFragment.newInstance(gameStat);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(GameStatActivity.this, GameHistoryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
