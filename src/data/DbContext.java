package data;

import java.sql.*;
import java.util.ArrayList;
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
    
    private Connection connect() {
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(this.url, "sa", "Admin23#");
                System.out.println("Connexion réussie !");
                return conn; 
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private Statement cursor() {
        try {
                /*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(this.url, "sa", "Admin23#");
                System.out.println("Connexion réussie !");
                
                stmt = conn.createStatement();*/
                
                stmt = connect().createStatement();

                return stmt; 
        } catch (Exception ex) {
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
    
    public void insertIntoTable(Connection conn, String tableName, String[] columns, Object[] values) throws SQLException {
        // Créer une requête SQL dynamique avec les paramètres fournis
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append(" VALUES (");
        for (int i = 0; i < values.length; i++) {
            sb.append("?");
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        String sql = sb.toString();
        
        // Créer une instruction préparée avec la requête SQL et les valeurs fournies
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            pstmt.setObject(i+1, values[i]);
        }
        
        // Exécuter l'instruction préparée pour insérer les données dans la table
        pstmt.executeUpdate();
    }
    
    // Fonction pour récupérer les noms des colonnes d'une table
    public static String[] getColumnsFromTable(Connection conn, String tableName) throws SQLException {
        // Obtenir les métadonnées de la base de données pour la connexion donnée
        DatabaseMetaData metaData = conn.getMetaData();
        
        // Obtenir les noms de colonnes pour la table donnée
        ResultSet rs = metaData.getColumns(null, null, tableName, null);
        
        // Stocker les noms de colonnes dans un ArrayList
        ArrayList<String> columnList = new ArrayList<String>();
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            columnList.add(columnName);
        }
        
        // Convertir l'ArrayList en tableau de chaînes de caractères et le retourner
        String[] columns = new String[columnList.size()];
        columnList.toArray(columns);
        return columns;
    }

}
