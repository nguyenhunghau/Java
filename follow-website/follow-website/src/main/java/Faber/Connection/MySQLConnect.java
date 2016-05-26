package Faber.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nguyen Hung Hau
 */
public class MySQLConnect {

    private static final String SERVER_HOST = "172.30.4.132:3306";
    private static final String DB_EXTRACT = "FOLLOW_WEBSITE";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "faber@2016";

    //<editor-fold defaultstate="collapsed" desc="GET CONNECTION">
    public static Connection getConnection() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String strConnectionString = "jdbc:mysql://" + SERVER_HOST + "/" + DB_EXTRACT + "?useUnicode=true&characterEncoding=utf-8";
            connect = DriverManager.getConnection(strConnectionString, USERNAME, PASSWORD);
        } catch (Exception e) {
        }
        return connect;
    }
    //</editor-fold>
}
