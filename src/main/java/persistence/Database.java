package persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static Connection connection() throws SQLException {
        String url = "jdbc:sqlite:data/football_manager.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("errore");
        }
        
        Connection conn = DriverManager.getConnection(url);
        if (conn != null){

            try(Statement stmt = conn.createStatement()){
                stmt.execute("PRAGMA foreign_keys = ON;");    
            }
            
            

            return conn;
        }
        else
            throw new SQLException("Errore: la connessione passata è nulla!");

        
            
       
    }
}