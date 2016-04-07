/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DTO.ClassStudy;
import DTO.Student;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>


/**
 *
 * @author root
 */
public class ClassStudyDao {
    
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get all students in database by ID and Name
     */
    public List getListClass(String courseId){
        
        List<ClassStudy> listClass = new ArrayList<ClassStudy>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Class where CourseID = " + courseId ;
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               ClassStudy classStudy = new ClassStudy();
               
               classStudy.setId(rs.getInt("ID"));
               classStudy.setName(rs.getString("NameClass"));
               classStudy.setId(rs.getInt("CourseID"));
               listClass.add(classStudy);
            }
            
            con.close();
            return listClass;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }
    }
}
