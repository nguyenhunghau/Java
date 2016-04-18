package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.SubjectDAO;
import DTO.SubjectDTO;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class SubjectBS {

    //<editor-fold defaultstate="collapsed" desc="get list subject">
    public String getListSubject() {

        SubjectDAO subjectDao = new SubjectDAO();
        Gson gson = new Gson();

        try {
            List<SubjectDTO> listSubject = subjectDao.getListSubject();
            String result = gson.toJson(listSubject);
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(SemesterBS.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
//</editor-fold>
}
