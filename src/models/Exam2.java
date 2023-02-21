package models;

import data.DbContext;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Exam2{

    
    /*public static void main(String[] args) {
        DbContext db = new DbContext();
        // TODO code application logic here
        System.out.println("Hello");
        ResultSet rs = db.query("SELECT * FROM auto_owners");
        
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber + " columns");
        } catch (SQLException ex) {
            Logger.getLogger(Exam2.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exam2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
}
