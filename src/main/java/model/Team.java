package model;

import java.util.List;
import java.util.ArrayList;

public class Team {

    private String name;
    private List<Player> players;
    private int budget;

    public Team(String nameTeam) {

         if (nameTeam == null || nameTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del team non può essere vuoto.");
        }

        this.name = nameTeam;
        this.players = new ArrayList<>();
        this.budget = 0;
    
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void insertPlayerOnTeam(Player teamPlayer) {

        if (teamPlayer == null) {
            throw new IllegalArgumentException("Necessario inserire un giocatore.");
        }
        this.players.add(teamPlayer);
    }
}