package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.ClassStudyDTO;
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
public class ClassStudyDAO {

    PreparedStatement prepareState;
    ResultSet resultSet;
    String strSql;

    //<editor-fold defaultstate="collapsed" desc="Get list class">
    public List<ClassStudyDTO> getListClass(String strCourseId) throws SQLException {

        List<ClassStudyDTO> listClass = new ArrayList<>();
        Connection con = getConnection();

        if (strCourseId.equals("")) {
            strSql = "SELECT * FROM Class";
        } else {
            strSql = "SELECT * FROM Class WHERE CourseId = ?";
        }

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            if (!strCourseId.equals("")) {
                prepareState.setString(1, strCourseId);
            }
            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {

                ClassStudyDTO classStudy = new ClassStudyDTO();

                classStudy.setId(resultSet.getInt("Id"));
                classStudy.setStrName(resultSet.getString("NameClass"));
                classStudy.setCourseId(resultSet.getInt("CourseId"));
                listClass.add(classStudy);
            }

        } catch (Exception ex) {
            System.err.println("Error Connect ");
            return listClass;

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

            return listClass;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get class">
    public ClassStudyDTO getClass(String strClassId) throws SQLException {

        ClassStudyDTO classStudy = new ClassStudyDTO();
        Connection con = getConnection();
        strSql = "SELECT * FROM Class WHERE Id = ?";

        try {

            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, strClassId);
            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {
                classStudy.setId(resultSet.getInt("Id"));
                classStudy.setStrName(resultSet.getString("NameClass"));
                classStudy.setCourseId(resultSet.getInt("CourseId"));
            }

        } catch (Exception ex) {
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

            return classStudy;
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add new class">
    public boolean addNewClass(ClassStudyDTO classStudy) throws SQLException {

        Connection con = getConnection();
        strSql = "INSERT INTO  Class (NameClass,CourseId) VALUES (?,?)";

        try {

            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, classStudy.getStrName());
            prepareState.setInt(2, classStudy.getCourseId());
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Error Connect ");
            return false;

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
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update class">
    public boolean updateClass(ClassStudyDTO classStudy) throws SQLException {
        
        Connection con = getConnection();
        strSql = "UPDATE Class set NameClass  = ?, CourseId = ? WHERE Id = ?";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, classStudy.getStrName());
            prepareState.setInt(2, classStudy.getCourseId());
            prepareState.setInt(3, classStudy.getId());
            String s  = prepareState.toString();
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
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
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Delete class">
    public boolean deleteClass(int strId) throws SQLException {
        
        Connection con = getConnection();
        strSql = "DELETE FROM Class WHERE Id = ?";

        try {
            
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setInt(1, strId);
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
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
        }
    }
//</editor-fold>
    
}
