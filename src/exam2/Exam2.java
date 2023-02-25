package exam2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Exam2{

    
    public static void main(String[] args) {
        try {
            AppInterface appInterface = new AppInterface();
        } catch (Exception ex) {
            Logger.getLogger(Exam2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
