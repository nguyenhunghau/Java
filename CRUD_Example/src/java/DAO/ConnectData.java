/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//</editor-fold>

/**
 *
 * @author root
 */
public class ConnectData {
    private Connection con;
    private Statement statement;
    private static String connectionString = "jdbc:mysql://172.30.4.230:3306/STUDENT_MANAGER";
    private String username = "root";
    private String password = "hunghauit117";
     
    public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");       
            con = DriverManager.getConnection(connectionString,username,password);
            System.out.print("Connect successful");
            return con;
        } catch (Exception ex) {
             System.out.print("Can't not connect");
             return null;
        }
        
    }
     
    public void close(){
        
        try {
            statement.close();
            con.close();
        } catch (SQLException ex) {
            System.out.print("Error Close");
        }
        
    }
}
