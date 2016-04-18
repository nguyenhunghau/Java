package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.ScoreBS;
import DTO.ScoreDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//</editor-fold>

/**
 *
 * @author Nguyen Hung hau
 */
public class ScoreServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Get function">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
// </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Post function">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        ScoreBS scoreBS = new ScoreBS();
        ScoreDTO score = new ScoreDTO();
        PrintWriter out = response.getWriter();
        String strPath = request.getServletPath();
        String strStudentId = request.getParameter("StudentId");
        String result = "";

        try {

            switch (strPath) {
                case "/getListScore":
                    String strSemesterId = request.getParameter("SemesterId");
                    result = scoreBS.getListScore(strStudentId, strSemesterId);
                    break;
                case "/getScore":
                    String strId = request.getParameter("id");
                    result = scoreBS.getScore(strId);
                    break;
                case "/updateScore":
                    String json = request.getParameter("json");
                    result = scoreBS.updateScore(json);
                    break;
                case "/deleteScore":
                    String Id = request.getParameter("Id");
                    result = scoreBS.deleteScore(Integer.valueOf(Id));
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            out.print(result);
        }
    }
// </editor-fold>

}
