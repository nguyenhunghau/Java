 package Faber.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Hung Hau
 */
public class MySQLConnect {

    public static Connection getConnection() {
         try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String strConnectionString = "jdbc:mysql://172.30.4.230:3306/SAVE_WEBSITE?useUnicode=true&characterEncoding=utf-8";
            Connection con = DriverManager.getConnection(strConnectionString, "root", "hunghauit117");
            System.out.print("Connect successful");
            return con;
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Can't not connect");
            return null;
        }
    }
}
