package service;

import src.main.java.persistence.TeamRepository;

public class TeamService{

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public associateTeamToManager(int teamId, int budget, int managerId, int purchasePrice){

        TeamRepository teamRepository = new TeamRepository();

        Team team = teamRepository.getNameTeam(teamId);

        
        
    }
}