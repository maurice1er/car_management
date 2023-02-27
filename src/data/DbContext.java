package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        System.out.println("Connexion réussie !");
    }

    
    private Connection connect() {
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(this.url, "sa", "Admin23#");
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
    
    public int insertIntoTable(String tableName, String[] columns, Object[] values, int skip_value) throws SQLException{
        try{
        // Créer une requête SQL dynamique avec les paramètres fournis
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append(" (");
        for (int i = 0; i < columns.length; i++) {
            
            // skip first column
            if (i < skip_value && skip_value != 0) {
                continue;
            }
            
            sb.append(columns[i]);
            if (i < columns.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < values.length; i++) {
            
            sb.append("?");
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        String sql = sb.toString();
        
        // CARS_Id, MATRICULE
        /*sb.delete(0, 0);
        sb.delete(0, 0);*/
        
        //System.out.println(sql);
        
        // Créer une instruction préparée avec la requête SQL et les valeurs fournies
        PreparedStatement pstmt = connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.length; i++) {
            //System.out.println(i + " --->" + values[i] + ","); 
            pstmt.setObject(i+1, values[i]);
        }
        
        // Exécuter l'instruction préparée pour insérer les données dans la table
        int affectedRows = pstmt.executeUpdate();   

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getLong(1));
                return generatedKeys.getInt(1);
            } 
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }   
        }finally {
            if (connect() != null) {
                connect().close();
            }
        }
    }
        
    public ResultSet queryPrepare(String tableName, String[] keys, String[] symbols, String[] values, int skip_value) throws SQLException{
        
        // Créer une requête SQL dynamique avec les paramètres fournis
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        for (int i = 0; i < keys.length; i++) {
            
            // skip first column
            if (i < skip_value && skip_value != 0) {
                continue;
            }
            
            sb.append(keys[i]);
            sb.append(symbols[i]);
            sb.append("'");
            sb.append(values[i]);
            sb.append("'");
            
            if (i < keys.length - 1) {
                sb.append(", ");
            }
        }
        
        String sql = sb.toString();
        System.out.println(sql);
        
        ResultSet rs = query(sql);
        
        return rs;
    }
        
    public int queryPrepareId(String tablename, String returnColumn, String filterColumn, String value) throws SQLException{
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(returnColumn);
            sb.append(" FROM ");
            sb.append(tablename);
            sb.append(" WHERE ");
            sb.append(filterColumn);
            sb.append("= ?");
            
            String sql = sb.toString();
            //System.out.println(sql);
            PreparedStatement stmt = connect().prepareStatement(sql);
            stmt.setString(1, value); 
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt(returnColumn); 
                //System.out.println("ID de l'utilisateur : " + id);
                return id;
            } else {
                //System.out.println("Aucun utilisateur trouvé.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : ---> " + e.getMessage());
        }   
        return -1;
    }
    
    // Fonction pour récupérer les noms des colonnes d'une table
    public String[] getColumnsFromTable(String tableName) throws SQLException {
        try{
        // Obtenir les métadonnées de la base de données pour la connexion donnée
        DatabaseMetaData metaData = connect().getMetaData();
        
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
        
        }finally{
            if (connect() != null) {
                connect().close();
            }
        }
    }
    
    
    
    public void displayTable(ResultSet rs, JTable table) {
        try {

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            
            

            Vector columns = new Vector(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                columns.add(md.getColumnName(i));
            }
            /*System.out.println(columns);
            System.out.println("");*/
            

            Vector data = new Vector();
            Vector row;

            while (rs.next()) {
                row = new Vector(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                
                data.add(row);
            }
            /*System.out.println(data);
            System.out.println("");*/

            DefaultTableModel tableModel = new DefaultTableModel(data, columns);
            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
