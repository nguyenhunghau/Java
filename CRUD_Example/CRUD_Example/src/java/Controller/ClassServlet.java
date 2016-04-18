package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.ClassStudyBS;
import DTO.ClassStudyDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class ClassServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClassStudyBS classBS = new ClassStudyBS();
        PrintWriter out = response.getWriter();
        String strPath = request.getServletPath();
        Gson gson = new Gson();
        ClassStudyDTO classStudy = null;
        String result = "";
        String parameter = "";
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");

        try {
            switch (strPath) {

                case "/getListClass":
                    parameter = request.getParameter("course");
                    result = classBS.getListClass(parameter);
                    break;

                case "/getClass":
                    parameter = request.getParameter("id");
                    result = classBS.getClass(parameter);
                    break;

                case "/addNewClass":
                    parameter = request.getParameter("json");
                    result = classBS.addNewClass(parameter);
                    break;

                case "/updateClass":
                    parameter = request.getParameter("json");
                    classStudy = gson.fromJson(parameter, ClassStudyDTO.class);
                    result = classBS.updateClass(classStudy);
                    break;
                case "/deleteClass":
                    parameter = request.getParameter("Id");
                    result = classBS.deleteClass(Integer.valueOf(parameter));
                    break;
                default:
                    break;
            }

        } catch (Exception e) {

            System.out.println("Error");
            
        } finally {
            out.print(result);
        }
    }
    // </editor-fold>

}
