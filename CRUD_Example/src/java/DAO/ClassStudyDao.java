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
import java.sql.SQLException;
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
    public List getListClass(String strCourseId) throws SQLException{
        
        List<ClassStudy> listClass = new ArrayList<ClassStudy>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Class where CourseID = ?";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, strCourseId);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               ClassStudy classStudy = new ClassStudy();
               
               classStudy.setId(rs.getInt("ID"));
               classStudy.setName(rs.getString("NameClass"));
               classStudy.setCourseId(rs.getInt("CourseID"));
               listClass.add(classStudy);
            }
            
            return listClass;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
    
    public ClassStudy getClass(String strClassId) throws SQLException{
        
        List<ClassStudy> listClass = new ArrayList<ClassStudy>();
        ClassStudy classStudy = new ClassStudy();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Class where ID = ?";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, strClassId);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
               classStudy.setId(rs.getInt("ID"));
               classStudy.setName(rs.getString("NameClass"));
               classStudy.setCourseId(rs.getInt("CourseID"));
            }
            
            return classStudy;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
    
    public boolean addNewClass(ClassStudy classStudy) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "insert into  Class (NameClass,CourseID) values (?,?)";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, classStudy.getName());
            prepareState.setInt(2, classStudy.getCourseId());
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }   finally {
            con.close();
         }
    }
    
    public boolean updateClass(ClassStudy classStudy) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "update Class set NameClass = ?,CourseID = ? where ID = ?";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, classStudy.getName());
            prepareState.setInt(2, classStudy.getCourseId());
            prepareState.setInt(3, classStudy.getId());
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }   finally {
            con.close();
         }
    }
    
    public boolean deleteClass(int strId) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "delete from Class where ID = ?";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setInt(1, strId);
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }   finally {
            con.close();
         }
    }
}
