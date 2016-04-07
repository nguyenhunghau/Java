/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Score;
import DTO.Semester;
import DTO.Student;
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
public class ScoreDao {
    
    static private ConnectData connect = new ConnectData();
    
    /**
     * Get Score with StudentId and SemeterId
     */
    public List getListScore(String studentId, String semesterId){
        
        List<Score> listScore = new ArrayList<Score>();
        //Get connection with mysql server
        Connection con = connect.getConnection();
        PreparedStatement prepareState;
        
        String sql = "";
        if(semesterId.equals(""))
            sql = "select A.ID, A.StudentID, A.SemesterID,A.SubjectID, A.Score1,A.Score2,A.Score3,B.NameSubject, C.NameSemester from Score A, Subject B, Semester C where A.StudentID = ? and A.SubjectID = B.ID and A.SemesterID = C.ID";
        else
            sql = "select A.ID, A.StudentID, A.SemesterID,A.SubjectID, A.Score1,A.Score2,A.Score3,B.NameSubject, C.NameSemester from Score A, Subject B, Semester C where A.StudentID = ? and A.SemesterID = ? and A.SubjectID = B.ID and A.SemesterID = C.ID";
            
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, studentId);
            if(!semesterId.equals(""))
                prepareState.setInt(2,Integer.valueOf(semesterId));

            ResultSet rs = prepareState.executeQuery();
            
            while(rs.next()) {
                
               Score score = new Score();
               score.setId(rs.getInt("ID"));
               score.setScrore_1(rs.getFloat("Score1"));
               score.setScrore_2(rs.getFloat("Score2"));
               score.setScrore_3(rs.getFloat("Score3"));
               score.setStudentId(rs.getString("StudentID"));
               score.setSemesterId(rs.getInt("SemesterID"));
               score.setSubjectId(rs.getInt("SubjectID"));
               score.setAverage(getAverageScore(score.getScrore_1(), score.getScrore_2(), score.getScrore_3()));
               score.setNameSubject(rs.getString("NameSubject"));
               score.setStrNameSemester(rs.getString("NameSemester"));
               listScore.add(score);
            }
            
            con.close();
            return listScore;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }
    }
    
    public Score getScore(String strId){
        
        //Get connection with mysql server
        Connection con = connect.getConnection();
        PreparedStatement prepareState;
        Score score = new Score();
        String sql = "select * from Score where ID = ?";
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setInt(1, Integer.valueOf(strId));

            ResultSet rs = prepareState.executeQuery();
            String s = prepareState.toString();
            while(rs.next()) {
               score.setScrore_1(rs.getFloat("Score1"));
               score.setScrore_2(rs.getFloat("Score2"));
               score.setScrore_3(rs.getFloat("Score3"));
               score.setSubjectId(rs.getInt("SubjectID"));
            }
            
            con.close();
            return score;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }
    }
    
    private float getAverageScore(float score1, float score2, float score3){
        return (float) Math.round((score1 + score2*2 + score3* 3)/6 * 100) / 100;
    }
    
    public boolean deleteScore(String Id)
    {
        Connection con = connect.getConnection();
        String sql="DELETE FROM Score WHERE ID = ?";
        PreparedStatement prepareState;
        
        try{
            prepareState=(PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, Id);
            prepareState.executeUpdate();
            con.close();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    } 
    
    public boolean addNewScore(Score score){
        Connection con = connect.getConnection();
        String sql = "insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values (?,?,?,?,?,?)";
        
        PreparedStatement prepareState;
        
        try{
            
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, score.getStudentId());
            prepareState.setInt(2, score.getSemesterId());
            prepareState.setInt(3, score.getSubjectId());
            prepareState.setFloat(4, score.getScrore_1());
            prepareState.setFloat(5, score.getScrore_2());
            prepareState.setFloat(6, score.getScrore_3());
            prepareState.executeUpdate();
            con.close();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         } 
    }
    
    public boolean updateScore(Score score) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "update Score SET SemesterID = ? , SubjectID = ?,Score1 = ?"
                        + ", Score2 = ? , Score3 = ? where StudentID = ?";
       
        PreparedStatement prepareState;
        
        try{
            
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            
            prepareState.setInt(1, score.getSemesterId());
            prepareState.setInt(2, score.getSubjectId());
            prepareState.setFloat(3, score.getScrore_1());
            prepareState.setFloat(4, score.getScrore_2());
            prepareState.setFloat(5, score.getScrore_3());
            prepareState.setString(6, score.getStudentId());
            String s = prepareState.toString();
            prepareState.executeUpdate();
            con.close();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             con.close();
             return false;
         } 
    }
}
