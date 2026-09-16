package persistence;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.sql.Connection; 

import model.Team;

public class TeamRepository{
    public void associateTeamToManager(Team team, int purchasePrice, int managerId)throws SQLException {
        
        try (Connection connection = Database.connection()){
        String sql = "INSERT INTO owner_manager_id (name, purchase_price, budget_eur, owner_manager_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, team.getName()); 
            pstmt.setInt(2, purchasePrice);
            pstmt.setInt(3, team.getBudget());
            pstmt.setInt(4, managerId);
     
    
            pstmt.executeUpdate(); 

          
        }
 
       
            
            throw new SQLException("Errore: nessuna riga recuperata!");
            } 
       
    
    }

    public Optional<Team> getNameTeam(int id) throws SQLException{

         try (Connection connection = Database.connection()){

            String sqlSelect = "SELECT * FROM manager WHERE id = ?";
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

            String sqlSelect = "SELECT * FROM manager WHERE id = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setLong(1, id);
                try (ResultSet getTeam = pstmtSelect.executeQuery()) {
                    if (getTeam.next()) {
                        return getTeam.getInt("purchase_price_eur");
                    }
                    return 0;
                }
            }
                    
                    

        } 

    }

    
}