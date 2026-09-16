package persistence;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.sql.Connection; 

import model.Team;

public class TeamRepository{
    public void associateTeamToManager(Team team, int purchasePrice, int managerId) throws SQLException {
        
        try (Connection connection = Database.connection()){
        String sqlSelect = "SELECT * FROM team WHERE name = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setString(1, team.getName());
                try (ResultSet getTeam = pstmtSelect.executeQuery()) {
                    if (getTeam.next()) {
                        int clubId = getTeam.getInt("club_id");                   
                     
                        String sql = "UPDATE club SET owner_manager_id = ? WHERE id = ?";

                        
                        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                            pstmt.setInt(1, managerId);
                            pstmt.setInt(2, clubId); 
            
            
            
     
    
            pstmt.executeUpdate(); 

          
        }
 
       
            

                    }
                   
                }
            }
        }
        
       
    
    }

    public Optional<Team> getNameTeam(int id) throws SQLException{

         try (Connection connection = Database.connection()){

            String sqlSelect = "SELECT * FROM team WHERE id = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setLong(1, id);
                try (ResultSet getTeam = pstmtSelect.executeQuery()) {
                    if (getTeam.next()) {
                        Team team = new Team(getTeam.getString("name"));
                        return Optional.of(team);
                    }
                    return Optional.empty();
                }
            }
                    
                    

        } 

    }

    public int getPurchasePrice(int id) throws SQLException{

         try (Connection connection = Database.connection()){

            String sqlSelect = "SELECT * FROM team WHERE id = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setLong(1, id);
                try (ResultSet getTeam = pstmtSelect.executeQuery()) {
                    if (getTeam.next()) {
                        int clubId = getTeam.getInt("club_id");

                        String sqlSelectClub = "SELECT * FROM club WHERE id = ?";
                        try (PreparedStatement pstmtSelectClub = connection.prepareStatement(sqlSelectClub)) {
                            pstmtSelectClub.setLong(1, id);
                            try (ResultSet getClub = pstmtSelectClub.executeQuery()) {
                                if (getClub.next()) {
                                    return getClub.getInt("purchase_price_eur");
                                }
                                return 0;
                            }
                        }
                    }
                    return 0;
                }
            }
                    
                    

        } 

    }

    
}