package service;

import java.sql.SQLException;
import java.util.Optional;

import model.Team;
import persistence.TeamRepository;


public class TeamService{

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Optional<Team> associateTeamToManager(int teamId, int budget, int managerId, int purchasePrice) throws SQLException{

        return teamRepository.getNameTeam(teamId);
        
        
    }
}