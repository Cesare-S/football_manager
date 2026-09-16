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

    public void associateTeamToManager(int teamId, int managerId) throws SQLException{

        Optional<Team> teamOptional = teamRepository.getNameTeam(teamId);

        if (!teamOptional.isPresent()){
            throw new IllegalArgumentException("Errore: team non trovato!");
        }

        int purchasePrice = teamRepository.getPurchasePrice(teamId);
        
        
        teamRepository.associateTeamToManager(teamOptional.get(), purchasePrice, managerId);
        
        
    }
}