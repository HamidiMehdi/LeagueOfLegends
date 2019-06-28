package com.quizzy.mrk.leagueoflegends;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.quizzy.mrk.leagueoflegends.Enum.RegionEnum;
import com.quizzy.mrk.leagueoflegends.Requests.SearchChampions;
import com.quizzy.mrk.leagueoflegends.Requests.SearchPlayer;
import com.quizzy.mrk.leagueoflegends.Requests.SearchSpells;
import com.quizzy.mrk.leagueoflegends.Services.ChampionService;
import com.quizzy.mrk.leagueoflegends.Services.SpellService;

import java.util.ArrayList;

public class SearchPlayerActivity extends AppCompatActivity {

    private Spinner spRegion;
    private EditText etPlayerName;
    private Button bSearch;

    private ArrayList<String> regions = RegionEnum.getRegionList();
    private SearchPlayer searchRequest;
    private SearchChampions searchChampions;
    private SearchSpells searchSpells;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        this.spRegion = findViewById(R.id.region);
        this.etPlayerName = findViewById(R.id.player_name);
        this.bSearch = findViewById(R.id.search);
        this.searchRequest = new SearchPlayer(this);
        this.searchChampions = new SearchChampions(this);
        this.searchSpells = new SearchSpells(this);

        this.hydrateRegionList();

        this.pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage(getString(R.string.search));

        this.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlayer();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void hydrateRegionList() {
        ArrayAdapter<String> aaRegion = new ArrayAdapter<>(this, R.layout.adater_spinner_item, this.regions);
        aaRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spRegion.setAdapter(aaRegion);
    }

    private void searchPlayer() {
        if (this.check()) {

            this.pDialog.show();
            this.searchRequest.searchPlayerRequest(RegionEnum.getRegion(
                    this.spRegion.getSelectedItem().toString()),
                    this.etPlayerName.getText().toString().trim(),
                    new SearchPlayer.SearchPlayerCallback() {
                        @Override
                        public void onSuccess() {
                            searchChampions();
                        }

                        @Override
                        public void dontExist() {
                            pDialog.hide();
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.activity_search_player), R.string.player_not_found, 2500);
                            snackbar.show();
                        }

                        @Override
                        public void onErrorNetwork() {
                            pDialog.hide();
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.activity_search_player), R.string.error_network, 2500);
                            snackbar.show();
                        }

                        @Override
                        public void onErrorVolley() {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.activity_search_player), R.string.error_volley, 2500);
                            snackbar.show();
                        }
                    }
            );
        }
    }

    private void searchChampions() {
        if (ChampionService.getChampionService() == null) {
            this.searchChampions.getAllChampions(new SearchChampions.ChampionCallback() {
                @Override
                public void onSuccess() {
                    searchSpells();
                }

                @Override
                public void onErrorNetwork() {
                    pDialog.hide();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_search_player), R.string.error_network, 2500);
                    snackbar.show();
                }

                @Override
                public void onErrorVolley() {
                    pDialog.hide();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_search_player), R.string.error_volley, 2500);
                    snackbar.show();
                }
            });
        } else {
            this.searchSpells();
        }
    }

    private void searchSpells() {
        if (SpellService.getSpellService() == null) {
            this.searchSpells.getAllSpells(new SearchSpells.SpellCallback() {
                @Override
                public void onSuccess() {
                    changeActivity();
                }

                @Override
                public void onErrorNetwork() {
                    pDialog.hide();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_search_player), R.string.error_network, 2500);
                    snackbar.show();
                }

                @Override
                public void onErrorVolley() {
                    pDialog.hide();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_search_player), R.string.error_volley, 2500);
                    snackbar.show();
                }
            });
        } else {
            this.changeActivity();
        }
    }

    private void changeActivity() {
        this.pDialog.hide();
        Intent intent = new Intent(SearchPlayerActivity.this, GameHistoryActivity.class);
        startActivity(intent);
    }

    private boolean check() {
        boolean check = true;
        if (this.etPlayerName.getText().toString().matches("")) {
            this.etPlayerName.setError(getString(R.string.et_error_empty));
            check = false;
        } else {
            this.etPlayerName.setError(null);
        }

        return check;
    }
}