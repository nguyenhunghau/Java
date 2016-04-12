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
    public List getListScore(String studentId, String semesterId) throws SQLException{
        
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
            
            return listScore;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        } finally {
            con.close();
        }
    }
    
    public Score getScore(String strId) throws SQLException{
        
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
               score.setStudentId(rs.getString("StudentID"));
               score.setScrore_1(rs.getFloat("Score1"));
               score.setScrore_2(rs.getFloat("Score2"));
               score.setScrore_3(rs.getFloat("Score3"));
               score.setSubjectId(rs.getInt("SubjectID"));
            }
            
            return score;
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            return null;
        }  finally {
            con.close();
        }
    }
    
    private float getAverageScore(float score1, float score2, float score3){
        int count = 6;
        float score_1 =  score1;
        float score_2 =  score2;
        float score_3 =  score3;
        
        if(score_1 == -1){
            score_1 = 0;
            count--;
        }
        if(score_2 == -1){
            score_2 = 0;
            count = count - 2;
        }
        if(score_3 == -1){
            score_3 = 0;
            count = count - 3;
        }
        if(count == 0)
            return -1;
        float sum = score_1 + score_2*2 + score_3* 3 ;
        float result = (float)Math.round(sum/count * 100) / 100;
        return result;
    }
    
    public boolean deleteScore(String Id) throws SQLException
    {
        Connection con = connect.getConnection();
        String sql="DELETE FROM Score WHERE ID = ?";
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
    
    public boolean addNewScore(Score score) throws SQLException{
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
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }  finally {
            con.close();
        }
    }
    
    public boolean updateScore(Score score) throws SQLException{
        Connection con = connect.getConnection();
        String sql = "update Score SET SubjectID = ?,Score1 = ?"
                        + ", Score2 = ? , Score3 = ? where ID = ?";
       
        PreparedStatement prepareState;
        
        try{
            
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            
            prepareState.setInt(1, score.getSubjectId());
            prepareState.setFloat(2, score.getScrore_1());
            prepareState.setFloat(3, score.getScrore_2());
            prepareState.setFloat(4, score.getScrore_3());
            prepareState.setInt(5, score.getId());
            String s = prepareState.toString();
            prepareState.executeUpdate();
            return true;
            
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }  finally {
            con.close();
        }
    }
}
