/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ClassStudy;
import DTO.Semester;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class SemesterDao {
    
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get semester with Course ID
     */
    public List getListSemester(String courseId) throws SQLException{
        
        List<Semester> listSemester = new ArrayList<Semester>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Semester where CourseID = " + courseId ;
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               Semester semester = new Semester();
               
               semester.setId(rs.getInt("ID"));
               semester.setName(rs.getString("NameSemester"));
               listSemester.add(semester);
            }
            
            return listSemester;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }  finally {
            con.close();
        }
    }
}
