package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.ScoreDTO;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class ScoreDAO {

    Connection con = null;
    PreparedStatement prepareState = null;
    ResultSet resultSet = null;
    String strSql = "";

    //<editor-fold defaultstate="collapsed" desc="get list score">
    public List<ScoreDTO> getListScore(String strStudentId, String strSemesterId) throws SQLException {

        List<ScoreDTO> listScore = new ArrayList<>();
        con = getConnection();

        if (strSemesterId.equals("")) {
            strSql = "SELECT A.ID, A.StudentID, A.SemesterID,A.SubjectID, A.Score1,A.Score2,A.Score3,B.NameSubject, C.NameSemester FROM Score A, Subject B, Semester C where A.StudentID = ? and A.SubjectID = B.ID and A.SemesterID = C.ID";
        } else {
            strSql = "SELECT A.ID, A.StudentID, A.SemesterID,A.SubjectID, A.Score1,A.Score2,A.Score3,B.NameSubject, C.NameSemester FROM Score A, Subject B, Semester C where A.StudentID = ? and A.SemesterID = ? and A.SubjectID = B.ID and A.SemesterID = C.ID";
        }

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, strStudentId);
            if (!strSemesterId.equals("")) {
                prepareState.setInt(2, Integer.valueOf(strSemesterId));
            }

            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {

                ScoreDTO score = new ScoreDTO();
                score.setId(resultSet.getInt("Id"));
                score.setScrore_1(resultSet.getFloat("Score1"));
                score.setScrore_2(resultSet.getFloat("Score2"));
                score.setScrore_3(resultSet.getFloat("Score3"));
                score.setStrStudentId(resultSet.getString("StudentID"));
                score.setSemesterId(resultSet.getInt("SemesterID"));
                score.setSubjectId(resultSet.getInt("SubjectID"));
                score.setAverage(getAverageScore(score.getScrore_1(), score.getScrore_2(), score.getScrore_3()));
                score.setStrNameSubject(resultSet.getString("NameSubject"));
                score.setStrNameSemester(resultSet.getString("NameSemester"));
                listScore.add(score);
            }

        } catch (Exception ex) {
            System.err.println("Error Connect ");

        } finally {

            closeConnect();
            return listScore;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get score">
    public ScoreDTO getScore(String strId) throws SQLException {

        con = getConnection();
        ScoreDTO score = new ScoreDTO();
        strSql = "SELECT * FROM Score where ID = ?";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setInt(1, Integer.valueOf(strId));

            resultSet = prepareState.executeQuery();
            String s = prepareState.toString();
            while (resultSet.next()) {
                score.setStrStudentId(resultSet.getString("StudentID"));
                score.setScrore_1(resultSet.getFloat("Score1"));
                score.setScrore_2(resultSet.getFloat("Score2"));
                score.setScrore_3(resultSet.getFloat("Score3"));
                score.setSubjectId(resultSet.getInt("SubjectID"));
            }

        } catch (Exception ex) {
            System.err.println("Error Connect ");

        } finally {

            closeConnect();
            return score;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get average score">
    private float getAverageScore(float score1, float score2, float score3) {
        int count = 6;
        float score_1 = score1;
        float score_2 = score2;
        float score_3 = score3;

        if (score_1 == -1) {
            score_1 = 0;
            count--;
        }
        if (score_2 == -1) {
            score_2 = 0;
            count = count - 2;
        }
        if (score_3 == -1) {
            score_3 = 0;
            count = count - 3;
        }
        if (count == 0) {
            return -1;
        }
        float sum = score_1 + score_2 * 2 + score_3 * 3;
        float result = (float) Math.round(sum / count * 100) / 100;
        return result;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Delete score with student">
    public boolean deleteScore(String StudentId) throws SQLException {
        con = getConnection();
        String sql = "DELETE FROM Score WHERE StudentId = ?";
        PreparedStatement prepareState;

        try {
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setString(1, StudentId);
            prepareState.executeUpdate();
            return true;
        } catch (Exception ex) {
            return false;

        } finally {

            closeConnect();
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Delete score with Id">
    public boolean deleteScore(int Id) throws SQLException {
        con = getConnection();
        String sql = "DELETE FROM Score WHERE Id = ?";
        PreparedStatement prepareState;

        try {
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            prepareState.setInt(1, Id);
            prepareState.executeUpdate();
            return true;
        } catch (Exception ex) {
            return false;

        } finally {

            closeConnect();
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add new score">
    public boolean addNewScore(ScoreDTO score) throws SQLException {
        
        con = getConnection();
        strSql = "INSERT INTO Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values (?,?,?,?,?,?)";

        try {

            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, score.getStrStudentId());
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
            
        } finally {
            closeConnect();
            con.close();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update score">
    public boolean updateScore(ScoreDTO score) throws SQLException {
        
        con = getConnection();
        strSql = "update Score SET SubjectID = ?,Score1 = ?"
                    + ", Score2 = ? , Score3 = ? where ID = ?";

        try {

            prepareState = (PreparedStatement) con.prepareStatement(strSql);

            prepareState.setInt(1, score.getSubjectId());
            prepareState.setFloat(2, score.getScrore_1());
            prepareState.setFloat(3, score.getScrore_2());
            prepareState.setFloat(4, score.getScrore_3());
            prepareState.setInt(5, score.getId());
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
        } finally {
            closeConnect();
            con.close();
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Close connect">
     private void closeConnect() {

        try {

            if (con != null) {
                con.close();
            }

            if (prepareState != null) {
                prepareState.close();
            }

            if (resultSet != null) {
                prepareState.close();
            }
        } catch (Exception e) {
            System.out.println("Can not close connect");
        }

    }
//</editor-fold>
   
}
