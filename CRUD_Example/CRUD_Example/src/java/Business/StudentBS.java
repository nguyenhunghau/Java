package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.ScoreDAO;
import DAO.StudentDAO;
import DTO.StudentDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class StudentBS {

    //<editor-fold defaultstate="collapsed" desc="Declare Variable">
    StudentDAO studentDao = new StudentDAO();
    ScoreDAO scoreDao = new ScoreDAO();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get list student into json">
    public String getListStudent(String strId, String strName) throws SQLException {

        try {
            List<StudentDTO> listStudent = studentDao.getListStudent(strId, strName);
            Gson gson = new Gson();
            String result = gson.toJson(listStudent);
            return result;

        } catch (Exception e) {
            return "";
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get student into json">
    public String getStudent(String strId) throws SQLException {

        try {
            StudentDTO student = studentDao.getStudent(strId);
            Gson gson = new Gson();
            String result = gson.toJson(student);
            return result;

        } catch (Exception e) {
            return "";
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add new student">
    public String addNewStudent(String strStudent) {

        String strMessage = "";
        try {
            StudentDTO student = gson.fromJson(strStudent, StudentDTO.class);
            student.setStrId(studentDao.createId());
            
            if (studentDao.addNewStudent(student)) {
                strMessage = "Add student sucessful";
            } else {
                strMessage = "Can not Add student";
            }

        } catch (Exception e) {

            strMessage = "Exception when add new student";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update student">
    public String updateStudent(String strStudent) {

        String strMessage = "";
        try {

            StudentDTO student = gson.fromJson(strStudent, StudentDTO.class);
            if (studentDao.updateStudent(student)) {
                strMessage = "Update student sucessful";
            } else {
                strMessage = "Can not update student";
            }

        } catch (Exception e) {

            strMessage = "Exception when update student";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Delete student">
    public String deleteStudent(String strStudentId) {

        String strMessage = "";
        try {
            if (studentDao.deleteStudent(strStudentId)) {
                strMessage = "Add student sucessful";
            } else {
                strMessage = "Can not Add student";
            }

        } catch (Exception e) {

            strMessage = "Exception when add new student";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>
}
