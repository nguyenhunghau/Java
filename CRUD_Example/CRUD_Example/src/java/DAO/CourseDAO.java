package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.CourseDTO;
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
public class CourseDAO {
     
    //<editor-fold defaultstate="collapsed" desc="Get list course">
    public List<CourseDTO> getListCourse() throws SQLException{
        
        List<CourseDTO> listCourse = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT * FROM Course";
      
        PreparedStatement prepareState = null;
        ResultSet resultSet = null;
        
        try{
            prepareState = (PreparedStatement) con.prepareStatement(sql);
            resultSet = prepareState.executeQuery();
            
            while(resultSet.next()) {
                
               CourseDTO course = new CourseDTO();
               
               course.setId(resultSet.getInt("Id"));
               course.setStrYearBegin(resultSet.getString("YearBegin"));
               course.setStrYearEnd(resultSet.getString("YearEnd"));
               
               listCourse.add(course);
            }
            
        }
        catch(Exception ex){
            System.err.println("Error Connect ");
            
        } finally {
            if (con != null) {
                con.close();
            }

            if (prepareState != null) {
                prepareState.close();
            }

            if (resultSet != null) {
                prepareState.close();
            }
            
            return listCourse;
        }
    }
//</editor-fold>
    
}
