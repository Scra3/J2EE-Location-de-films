package Classes;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public Connection connexion(){
        Connection connexion = null;
        try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           connexion = DriverManager.getConnection("jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connexion;
    }
}
