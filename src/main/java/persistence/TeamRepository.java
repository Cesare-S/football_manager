package persistence;

public class TeamRepository{
    public void associateTeamToManager(Team team, int purchasePrice, int managerId)throws SQLException {
        
        try (Connection connection = Database.connection()){
        String sql = "INSERT INTO owner_manager_id (name, purchase_price, budget_eur, owner_manager_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, team.getName()); 
            pstmt.setString(2, purchasePrice);
            pstmt.setString(3, team.getBudget());
            pstmt.setString(3, managerId);
     
    
            pstmt.executeUpdate(); 

          
        }
 
       
            
            throw new SQLException("Errore: nessuna riga recuperata!");
            } 
       
    
    }

    public Optional<Team> getNameTeam(int id){

         try (Connection connection = Database.connection()){

            String sqlSelect = "SELECT * FROM manager WHERE id = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setLong(1, id);
                try (ResultSet getManager = pstmtSelect.executeQuery()) {
                    if (getManager.next()) {
                        Team team = new Team(getManager.getString("name"));
                        return Optional.of(team);
                    }
                    return Optional.empty();
                }
            }
                    
                    

        } 

    }
}