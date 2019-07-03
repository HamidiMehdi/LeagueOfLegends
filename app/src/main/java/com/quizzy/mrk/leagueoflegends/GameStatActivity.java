package com.quizzy.mrk.leagueoflegends;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.quizzy.mrk.leagueoflegends.Entities.GameStat;
import com.quizzy.mrk.leagueoflegends.Fragment.GameFragment;
import com.quizzy.mrk.leagueoflegends.Fragment.StatFragment;

public class GameStatActivity extends AppCompatActivity {

    private GameStat gameStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stat);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.game);
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


}
