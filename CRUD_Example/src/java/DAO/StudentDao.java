/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DTO.Student;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
//</editor-fold>


/**
 *
 * @author root
 */
public class StudentDao {
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get all students in database by ID and Name
     */
    public List getListStudent(String Id, String Name) throws SQLException{
        
        List<Student> listStudent = new ArrayList<Student>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Student where ID like ? and NameStudent like ?";
      
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, "%" + Id + "%");
            prepareState.setString(2, "%" + Name + "%");
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               Student student = new Student();
               
               student.setId(rs.getString("ID"));
               student.setAddress(rs.getString("Address"));
               student.setBirthday(rs.getDate("Birthday"));
               student.setGender(rs.getString("Gender"));
               student.setName(rs.getString("NameStudent"));
               student.setReceiveDay(rs.getDate("ReceiveDay"));
               student.setClassId(rs.getInt("ClassID"));
               listStudent.add(student);
            }
            
            return listStudent;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }  finally {
            con.close();
        }
    }
    
    public Student getStudent(String Id) throws SQLException{
        //Get connection with mysql server
        Connection con = connect.getConnection();
        String sql = "select * from Student where ID = ? "; 
        Student student = new Student();
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, Id);
            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
               student.setId(rs.getString("ID"));
               student.setAddress(rs.getString("Address"));
               student.setBirthday(rs.getDate("Birthday"));
               student.setGender(rs.getString("Gender"));
               student.setName(rs.getString("NameStudent"));
               student.setReceiveDay(rs.getDate("ReceiveDay"));
                student.setClassId(rs.getInt("ClassID"));
            }
            
            return student;
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }  finally {
            con.close();
        }
    }
    
    public boolean addNewStudent(Student student) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "insert into Student(ID,NameStudent,Birthday,Gender,Address,ReceiveDay,ClassID) values (?,?,?,?,?,?,?)";
        
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, student.getId());
            prepareState.setString(2, student.getName());
            prepareState.setDate(3, student.getBirthday());
            prepareState.setString(4, student.getGender());
            prepareState.setString(5, student.getAddress());
            prepareState.setDate(6, student.getReceiveDay());
            prepareState.setInt(7, student.getClassId());
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }   finally {
            con.close();
         }
    }
    
    public boolean updateStudent(Student student) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "update Student SET NameStudent = ? , Birthday = ? , Gender = ?,"
                        + " Address= ? , ReceiveDay= ?, ClassID = ? where ID = ?";
       
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, student.getName());
            prepareState.setDate(2, student.getBirthday());
            prepareState.setString(3, student.getGender());
            prepareState.setString(4, student.getAddress());
            prepareState.setDate(5, student.getReceiveDay());
            prepareState.setInt(7, student.getClassId());
            prepareState.setString(7, student.getId());
            String path = prepareState.toString();
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         } 
    }
    
    public boolean deleteStudent(String Id) throws SQLException
    {
        Connection con = connect.getConnection();
        String sql="DELETE FROM Student WHERE ID = ?";
        PreparedStatement prepareState;
        
        try{
            prepareState=(PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, Id);
            prepareState.executeUpdate();
            return true;
        }
        catch(Exception ex){
            return false;
        }  finally {
            con.close();
        }
    } 
    /**
     * Create id when add new student
     */
    public String createId(){
         
        Connection con = connect.getConnection();
        String sql = "select * from Student order by ID desc ";
        //Date date = null;
        PreparedStatement prepareState;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = prepareState.executeQuery();
            //Get current year
            String year =  (Calendar.getInstance().get(Calendar.YEAR) + "").substring(2);
            while(rs.next()) {
                return year + getFormatId(Integer.parseInt(rs.getString("ID").substring(2)) + 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * when create id for student in database
     * it includes at least 4 letter
     */
    public String getFormatId(int Id){
        String result = String.valueOf(Id);
        for(int i = result.length(); i < 4; i++){
            result = "0" + result;
        }
        
        return result;
    }
}
