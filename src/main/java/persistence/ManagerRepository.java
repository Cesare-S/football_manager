package persistence;

import model.Manager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerRepository {
    
    public Manager saveManager(Manager manager) throws SQLException {
        
       String sql = "INSERT INTO manager (name, email, password, budget_eur) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
       if (pstmt){
            pstmt.setString(1, manager.getName()); 
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getPassword());
            pstmt.setLong(4, 50000000); 
    
            int righeInserite = pstmt.executeUpdate(); 

            int idInserito = -1; 

            if (righeInserite > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                
                if (rs.next()) {
                    idInserito = rs.getInt(1); 
                    String sqlSelect = "SELECT * FROM manager WHERE id = ?";
                    PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                    pstmtSelect.setInt(1, idInserito);
                    
                    ResultSet getManager = pstmtSelect.executeQuery();
                    if (getManager.next()) {
                        return new Manager(getManager->getInt("id"), manager.getName(), manager.getEmail(), manager.getPassword(), getManager->getInt("budget_eur"));
                    }
                    
                }
                
                rs.close();
            
            }
       }else{
        throw new SQLException("Errore: inserimento non riuscito!");
       }

    }


    public Manager getById(int id) throws SQLException{
                    String sqlSelect = "SELECT * FROM manager WHERE id = ?";
                    PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                    pstmtSelect.setInt(1, id);
                    
                    ResultSet getManager = pstmtSelect.executeQuery();
                    if (getManager.next()) {
                        return new Manager(getManager->getInt("id"), getManager->getString("name"), getManager->getString("email"), getManager->getString("password"), getManager->getInt("budget_eur"));
                    }

                    throw new SQLException("Errore: inserimento non riuscito!");
    }

    public Manager getByEmail(String email) throws SQLException{
                    String sqlSelect = "SELECT * FROM manager WHERE email = ?";
                    PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                    pstmtSelect.setString(1, email);
                    
                    ResultSet getManager = pstmtSelect.executeQuery();
                    if (getManager.next()) {
                        return new Manager(getManager->getInt("id"), getManager->getString("name"), getManager->getString("email"), getManager->getString("password"), getManager->getInt("budget_eur"));
                    }

                    throw new SQLException("Errore: inserimento non riuscito!");
    }


}