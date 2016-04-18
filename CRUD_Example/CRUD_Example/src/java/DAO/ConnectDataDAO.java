package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import java.sql.Connection;
import java.sql.DriverManager;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class ConnectDataDAO {

    private static Connection con;
    private static final String NAME_DATABASE = "STUDENT_MANAGER";
    private static final String IP = "127.0.0.1:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nguyenhunghau";
       
    public static Connection getConnection() {

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String strConnectionString = "jdbc:mysql://" + IP + NAME_DATABASE + "?useUnicode=true&characterEncoding=utf-8";
            con = DriverManager.getConnection(strConnectionString, USERNAME, PASSWORD);
            System.out.print("Connect successful");
            return con;
            
        } catch (Exception ex) {
            System.out.print("Can't not connect");
            return con;
        }

    }
}
