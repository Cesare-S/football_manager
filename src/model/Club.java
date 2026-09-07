package model;

public class Club {

    private String name;
    private Team team;

    public Club(String teamName, Team objTeam){

        if (teamName == null || teamName.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del club non può essere vuoto.");
        }

        if (objTeam == null) {
            throw new IllegalArgumentException("Il team non può essere vuoto");
        }

        this.name = teamName;
        this.team = objTeam;
    }

    public String getName() {
        return name;
    }

    public Team getTeam(){
        return team;
    }
}