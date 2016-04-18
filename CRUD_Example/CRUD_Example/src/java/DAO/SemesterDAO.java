package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.SemesterDTO;
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
public class SemesterDAO {
    
    Connection con = null;
    PreparedStatement prepareState = null;
    ResultSet resultSet = null;
    String strSql = "";
    
    //<editor-fold defaultstate="collapsed" desc="Get list semester">
    public List<SemesterDTO> getListSemester(String courseId) throws SQLException{
        
        List<SemesterDTO> listSemester = new ArrayList<>();
        con = getConnection();
        strSql = "SELECT * FROM Semester where CourseId = ?";
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setInt(1, Integer.valueOf(courseId));
            resultSet = prepareState.executeQuery();
            
            while(resultSet.next()) {
                
               SemesterDTO semester = new SemesterDTO();
               semester.setCourseId(resultSet.getInt("CourseID"));
               semester.setId(resultSet.getInt("Id"));
               semester.setStrName(resultSet.getString("NameSemester"));
               listSemester.add(semester);
            }
            
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            
        }  finally {
            closeConnect();
            return listSemester;
        }
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get semester">
    public SemesterDTO getSemester(String strSemesterId) throws SQLException{
        
        con = getConnection();
        strSql = "SELECT * FROM Semester where ID = ?" ;
        
        SemesterDTO semester = new SemesterDTO();
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setInt(1, Integer.valueOf(strSemesterId));
            resultSet = prepareState.executeQuery();
            
            while(resultSet.next()) {
               semester.setId(resultSet.getInt("Id"));
               semester.setStrName(resultSet.getString("NameSemester"));
               semester.setCourseId(resultSet.getInt("CourseID"));
            }
            
            
        } catch(Exception ex){
            System.err.println("Error Connect ");
            
        }  finally {
            closeConnect();
            return semester;
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
