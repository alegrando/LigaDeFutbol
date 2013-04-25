
package ligadefutbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    static Connection conexion = null;
    
    public static boolean conectar(String host, String usuario, String pass) {        
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://"+host+"/ligadefutbol",
                    usuario, pass);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
