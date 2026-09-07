package model;

import java.util.List;
import java.util.ArrayList;

public class Team {

    private String name;
    private List<Player> players;

    public Team(String nameTeam) {

         if (nameTeam == null || nameTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del team non può essere vuoto.");
        }

        this.name = nameTeam;
        this.players = new ArrayList<>();
    
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void insertPlayerOnTeam(Player teamPlayer) {

        if (teamPlayer == null) {
            throw new IllegalArgumentException("Necessario inserire un giocatore.");
        }
        this.players.add(teamPlayer);
    }
}