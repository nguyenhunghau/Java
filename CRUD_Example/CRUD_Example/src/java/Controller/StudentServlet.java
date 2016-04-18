package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.ScoreBS;
import Business.StudentBS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        PrintWriter out = response.getWriter();
        StudentBS studentBS = new StudentBS();
        ScoreBS scoreBS = new ScoreBS();
        String strPath = request.getServletPath();
        String strId = request.getParameter("Id");
        String result = "";
        try {

            switch (strPath) {
                case "/updateStudent":
                    String json = request.getParameter("json");
                    result = studentBS.updateStudent(json);
                    break;
                    
                case "/getListStudent":

                    if (strId == null) {
                        strId = "";
                    }

                    String strName = request.getParameter("Name");
                    if (strName == null) {
                        strName = "";
                    }

                    result = studentBS.getListStudent(strId, strName);
                    break;
                    
                case "/getStudent":

                    result = studentBS.getStudent(strId);
                    break;

                case "/addNewStudent":

                    json = request.getParameter("json");
                    result = studentBS.addNewStudent(json);
                    break;
                    
                case "/deleteStudent":
                    String strStudentId = request.getParameter("studentId");
                    result = scoreBS.deleteScore(strStudentId);
                    result = studentBS.deleteStudent(strStudentId);
                    break;
                default:
                    break;
            }
        } catch (SQLException | JsonSyntaxException e) {
            e.printStackTrace();
            
        } finally {
            out.print(result);
        }
    }
}
