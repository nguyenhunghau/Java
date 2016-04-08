/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DTO.Course;
import DTO.Student;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>


/**
 *
 * @author root
 */
public class CourseDao {
    
    static private ConnectData connect = new ConnectData();
     
    public List getListCourse() throws SQLException{
        
        List<Course> listCourse = new ArrayList<Course>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Course";
      
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               Course course = new Course();
               
               course.setId(rs.getInt("ID"));
               course.setYearBegin(rs.getString("YearBegin"));
               course.setYearEnd(rs.getString("YearEnd"));
               
               listCourse.add(course);
            }
            
            return listCourse;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
}
