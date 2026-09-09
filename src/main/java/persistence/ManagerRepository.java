package persistence;

import model.Manager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ManagerRepository {
    
    public Manager saveManager(Manager manager) throws SQLException {
        
        try (Connection con = Database.connection()){
        String sql = "INSERT INTO manager (name, email, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
       
            pstmt.setString(1, manager.getName()); 
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getPassword());
     
    
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
            } catch (SQLException e) {
            e.printStackTrace();
        }
       
       

    }


    public Manager getById(int id) throws SQLException{

        try (Connection con = Database.connection()){

            String sqlSelect = "SELECT * FROM manager WHERE id = ?";
                    PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                    pstmtSelect.setInt(1, id);
                    
                    ResultSet getManager = pstmtSelect.executeQuery();
                    if (getManager.next()) {
                        return new Manager(getManager->getInt("id"), getManager->getString("name"), getManager->getString("email"), getManager->getString("password"), getManager->getInt("budget_eur"));
                    }

                    throw new SQLException("Errore: inserimento non riuscito!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Manager getByEmail(String email) throws SQLException{

        try (Connection con = Database.connection()){
             String sqlSelect = "SELECT * FROM manager WHERE email = ?";
                    PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect);
                    pstmtSelect.setString(1, email);
                    
                    ResultSet getManager = pstmtSelect.executeQuery();
                    if (getManager.next()) {
                        return new Manager(getManager->getInt("id"), getManager->getString("name"), getManager->getString("email"), getManager->getString("password"), getManager->getInt("budget_eur"));
                    }

                    throw new SQLException("Errore: inserimento non riuscito!");
        } catch (SQLException e) {
                    e.printStackTrace();
                }
    }


}