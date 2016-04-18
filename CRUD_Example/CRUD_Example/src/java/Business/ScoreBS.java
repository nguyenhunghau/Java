package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.ScoreDAO;
import DTO.ScoreDTO;
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
public class ScoreBS {

    //<editor-fold defaultstate="collapsed" desc="Declare variable">
    ScoreDAO scoreDao = new ScoreDAO();
    Gson gson = new Gson();
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get list score">

    public String getListScore(String strStudentId, String strSemesterId) {
        
         try {
            List<ScoreDTO> listScore = scoreDao.getListScore(strStudentId, strSemesterId);
            String result = gson.toJson(listScore);
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SemesterBS.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get list score">

    public String getScore(String strId) {
        
         try {
            ScoreDTO score = scoreDao.getScore(strId);
            String result = gson.toJson(score);
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SemesterBS.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update score">
    public String updateScore(String json) {

        String strMessage = "";
        try {
            ScoreDTO score = gson.fromJson(json, ScoreDTO.class);
            
            if (scoreDao.updateScore(score)) {
                strMessage = "Update score sucessful";
            } else {
                strMessage = "Can not update score";
            }

        } catch (Exception e) {

            strMessage = "Exception when update score";

        } finally {
            return strMessage;
        }

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Delete score">
    public String deleteScore(String strStudentId) {

        String strMessage = "";
        try {
            if (scoreDao.deleteScore(strStudentId)) {
                strMessage = "Delete score sucessful";
            } else {
                strMessage = "Can not delete score";
            }

        } catch (Exception e) {

            strMessage = "Exception when delete score";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Delete score with Id">
    public String deleteScore(int Id) {

        String strMessage = "";
        try {
            if (scoreDao.deleteScore(Id)) {
                strMessage = "Delete score sucessful";
            } else {
                strMessage = "Can not delete score";
            }

        } catch (Exception e) {

            strMessage = "Exception when delete score";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>
}
