package Business;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.CourseDAO;
import DTO.CourseDTO;
import com.google.gson.Gson;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class CourseBS {

    CourseDAO courseDao = new CourseDAO();

    public String getListCourse() {

        try {
            List<CourseDTO> listCourse = courseDao.getListCourse();
            Gson gson = new Gson();
            String result = gson.toJson(listCourse);
            return result;

        } catch (Exception e) {
            return "";
        }
    }
}
