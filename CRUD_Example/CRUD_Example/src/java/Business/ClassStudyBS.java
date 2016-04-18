package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.ClassStudyDAO;
import DTO.ClassStudyDTO;
import com.google.gson.Gson;
import java.util.List;

//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class ClassStudyBS {

    ClassStudyDAO classDAO = new ClassStudyDAO();
    Gson gson = new Gson();

    //<editor-fold defaultstate="collapsed" desc="Get list class">
    public String getListClass(String strCourseId) {

        try {
            List<ClassStudyDTO> listClass = classDAO.getListClass(strCourseId);
            String result = gson.toJson(listClass);
            return result;

        } catch (Exception e) {
            return "";
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get class">
    public String getClass(String strId) {

        try {
            ClassStudyDTO classStudy = classDAO.getClass(strId);
            String result = gson.toJson(classStudy);
            return result;

        } catch (Exception e) {
            return "";
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add new class">
    public String addNewClass(String strClass){
        
        String strMessage = "";
        try{
            ClassStudyDTO classStudy = gson.fromJson(strClass, ClassStudyDTO.class);
            if(classDAO.addNewClass(classStudy))
                strMessage = "Add class sucessful";
            else
                strMessage = "Can not Add class";
            
        } catch (Exception e){
            
              strMessage = "Exception when add class";
              
        } finally {
            return strMessage;
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update class">
    public String updateClass(ClassStudyDTO classStudy){
        
        String strMessage = "";
        try{
            if(classDAO.updateClass(classStudy))
                strMessage = "Update class sucessful";
            else
                strMessage = "Can not update class";
            
        } catch (Exception e){
            
              strMessage = "Exception when update class";
              
        } finally {
            return strMessage;
        }
        
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Delete class">
    public String deleteClass(int Id) {

        String strMessage = "";
        try {
            if (classDAO.deleteClass(Id)) {
                strMessage = "Delete class sucessful";
            } else {
                strMessage = "Can not delete class";
            }

        } catch (Exception e) {

            strMessage = "Exception when delete class";

        } finally {
            return strMessage;
        }

    }
//</editor-fold>
}
