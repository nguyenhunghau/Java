package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.SemesterDAO;
import DTO.SemesterDTO;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 *
 * @author Nguyen Hung hau
 */
public class SemesterBS {
    
    SemesterDAO semesterDao = new SemesterDAO();
    Gson gson = new Gson();
    
    public String getListSemester(String strCourseId){
        
        try {
            List<SemesterDTO> listSemester = semesterDao.getListSemester(strCourseId);
            String result = gson.toJson(listSemester);
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SemesterBS.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    } 
}
