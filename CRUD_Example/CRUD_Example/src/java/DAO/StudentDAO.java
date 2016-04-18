package DAO;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import static DAO.ConnectDataDAO.getConnection;
import DTO.StudentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class StudentDAO {

    Connection con = null;
    PreparedStatement prepareState = null;
    ResultSet resultSet = null;
    String strSql = "";

    //<editor-fold defaultstate="collapsed" desc="Get list student">
    public List<StudentDTO> getListStudent(String Id, String Name) throws SQLException {

        List<StudentDTO> listStudent = new ArrayList<>();
        con = getConnection();
        String strSql = "SELECT * FROM Student WHERE Id like ? and NameStudent like ?";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, "%" + Id + "%");
            prepareState.setString(2, "%" + Name + "%");
            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {

                StudentDTO student = new StudentDTO();

                student.setStrId(resultSet.getString("ID"));
                student.setStrAddress(resultSet.getString("Address"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setStrGender(resultSet.getString("Gender"));
                student.setStrName(resultSet.getString("NameStudent"));
                student.setReceiveDay(resultSet.getDate("ReceiveDay"));
                student.setClassId(resultSet.getInt("ClassID"));
                listStudent.add(student);
            }

        } catch (Exception ex) {
            System.err.println("Error Connect ");

        } finally {
            closeConnect();
            return listStudent;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get student">
    public StudentDTO getStudent(String Id) throws SQLException {

        con = getConnection();
        strSql = "SELECT * FROM Student where Id = ? ";
        StudentDTO student = new StudentDTO();

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, Id);
            resultSet = prepareState.executeQuery();

            while (resultSet.next()) {
                student.setStrId(resultSet.getString("ID"));
                student.setStrAddress(resultSet.getString("Address"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setStrGender(resultSet.getString("Gender"));
                student.setStrName(resultSet.getString("NameStudent"));
                student.setReceiveDay(resultSet.getDate("ReceiveDay"));
                student.setClassId(resultSet.getInt("ClassID"));
            }

        } catch (Exception ex) {
            System.err.println("Error Connect ");

        } finally {
            closeConnect();
            return student;

        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add new student">
    public boolean addNewStudent(StudentDTO student) throws SQLException {

        con = getConnection();
        strSql = "INSERT INTO Student(Id,NameStudent,Birthday,Gender,Address,ReceiveDay,ClassID) VALUES (?,?,?,?,?,?,?)";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, student.getStrId());
            prepareState.setString(2, student.getStrName());
            prepareState.setDate(3, student.getBirthday());
            prepareState.setString(4, student.getStrGender());
            prepareState.setString(5, student.getStrAddress());
            prepareState.setDate(6, student.getReceiveDay());
            prepareState.setInt(7, student.getClassId());
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            closeConnect();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update student">
    public boolean updateStudent(StudentDTO student) throws SQLException {

        con = getConnection();
        strSql = "update Student SET NameStudent = ? , Birthday = ? , Gender = ?,"
                + " Address= ? , ReceiveDay= ?, ClassID = ? where Id = ?";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, student.getStrName());
            prepareState.setDate(2, student.getBirthday());
            prepareState.setString(3, student.getStrGender());
            prepareState.setString(4, student.getStrAddress());
            prepareState.setDate(5, student.getReceiveDay());
            prepareState.setInt(6, student.getClassId());
            prepareState.setString(7, student.getStrId());
            prepareState.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnect();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Delete student">
    public boolean deleteStudent(String Id) throws SQLException {

        con = getConnection();
        strSql = "DELETE FROM Student WHERE Id = ?";

        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            prepareState.setString(1, Id);
            prepareState.executeUpdate();
            return true;

        } catch (Exception ex) {
            return false;

        } finally {
            closeConnect();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Create id for student">
     public String createId() {

        con = getConnection();
        String strSql = "SELECT * FROM Student ORDER BY Id desc ";
        String strResult = "";
        try {
            prepareState = (PreparedStatement) con.prepareStatement(strSql);
            resultSet = prepareState.executeQuery();
            //Get current year
            String year = (Calendar.getInstance().get(Calendar.YEAR) + "").substring(2);
            while (resultSet.next()) {
                strResult = year + getFormatId(Integer.parseInt(resultSet.getString("Id").substring(2)) + 1);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
             
        } finally {
            closeConnect();
            return strResult;
        }
    }
//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Get for,at for ID student">
     public String getFormatId(int Id) {
        String strResult = String.valueOf(Id);
        for (int i = strResult.length(); i < 4; i++) {
            strResult = "0" + strResult;
        }
        return strResult;
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
