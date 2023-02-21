package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbContext {

    private static DbContext db = null;
    private Statement stmt = null;
    
    private final String url =
                "jdbc:sqlserver://localhost:11433;"
                + "database=automobile;"
                + "encrypt=true;"
                //+ "integratedSecurity=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=50;";
    
    public DbContext(){
        cursor();
    }

    /*public static DbContext getInstance() throws Exception {
        if (db == null) {
          db = new DbContext();
        }
        return db;
    }*/
    
    private Statement cursor() {
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(this.url, "sa", "Admin23#");
                System.out.println("Connexion réussie !");
                
                stmt = conn.createStatement();

                return stmt; 
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ResultSet query(String sql){
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    

}
