/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Rule;
import DTO.Semester;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.digester.CallMethodRule;

/**
 *
 * @author root
 */
public class RuleDao {
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get rule of school
     */
    public Rule getRule(String courseId) throws SQLException{
        Rule rule = null;
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Rule";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
               rule.setBenchmark(rs.getFloat("Benchmark"));
               rule.setID(rs.getInt("ID"));
               rule.setOldMax(rs.getInt("OldMax"));
               rule.setOldMin(rs.getInt("OldMin"));
               rule.setnMaxStudent(rs.getInt("nMaxStudent"));
               rule.setnMaxSubject(rs.getInt("nMaxSubject"));
            }
            
            return rule;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
}
