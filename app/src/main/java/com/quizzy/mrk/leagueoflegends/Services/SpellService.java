package com.quizzy.mrk.leagueoflegends.Services;

import com.quizzy.mrk.leagueoflegends.Entities.Spell;

import java.util.ArrayList;

public class SpellService {

    private static SpellService spellService = null;
    private ArrayList<Spell> spells = null;

    private SpellService(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public static void open(ArrayList<Spell> spells) {
        SpellService.spellService = new SpellService(spells);
    }

    public static SpellService getSpellService() {
        return SpellService.spellService;
    }

    public Spell getSpell(int spellKey) {
        Spell spell = null;

        for (Spell spellC : this.spells) {
            if (spellC.getKey() == spellKey) {
                spell = spellC;
                break;
            }
        }

        return spell;
    }
}
