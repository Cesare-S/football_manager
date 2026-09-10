package persistence;

import model.Manager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.sql.Connection;  

public class ManagerRepository {
    
    public Manager saveManager(Manager manager) throws SQLException {
        
        try (Connection connection = Database.connection()){
        String sql = "INSERT INTO manager (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, manager.getName()); 
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getPassword());
     
    
            int righeInserite = pstmt.executeUpdate(); 

            int idInserito = -1; 

            if (righeInserite > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()){

                
                if (rs.next()) {
                    idInserito = rs.getInt(1); 
                    String sqlSelect = "SELECT * FROM manager WHERE id = ?";
                    try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                        pstmtSelect.setInt(1, idInserito);
                        try (ResultSet getManager = pstmtSelect.executeQuery()) {
                            if (getManager.next()) {
                                return new Manager(getManager.getLong("id"), getManager.getString("name"), getManager.getString("email"), getManager.getString("password"), getManager.getInt("budget_eur"));
                            }
                        }
                        
                    }
                    
            
                }
                
                }
                
            
            }
        }
 
       
            
            throw new SQLException("Errore: nessuna riga recuperata!");
            } 
       
       

    }


    public Optional<Manager> getById(int id) throws SQLException{

        try (Connection connection = Database.connection()){

            String sqlSelect = "SELECT * FROM manager WHERE id = ?";
            try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                pstmtSelect.setLong(1, id);
                try (ResultSet getManager = pstmtSelect.executeQuery()) {
                    if (getManager.next()) {
                        Manager manager = new Manager(getManager.getLong("id"), getManager.getString("name"), getManager.getString("email"), getManager.getString("password"), getManager.getInt("budget_eur"));
                        return Optional.of(manager);
                    }
                    return Optional.empty();
                }
            }
                    
                    

        } 
    }

    public Optional<Manager> getByEmail(String email) throws SQLException{

        try (Connection connection = Database.connection()) {
             String sqlSelect = "SELECT * FROM manager WHERE email = ?";
                    try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
                        pstmtSelect.setString(1, email);
                        try (ResultSet getManager = pstmtSelect.executeQuery()) {
                            if (getManager.next()) {
                            Manager manager = new Manager(getManager.getLong("id"), getManager.getString("name"), getManager.getString("email"), getManager.getString("password"), getManager.getInt("budget_eur"));
                            return Optional.of(manager);
                        }
                        return Optional.empty();
                        }
                        }
                }
                    
    }


}