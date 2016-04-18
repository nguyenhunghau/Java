package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.SemesterBS;
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
public class SemesterServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Get function">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
// </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Post function">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        SemesterBS semesterBs = new SemesterBS();

        try {
            String strCourseId = request.getParameter("course");
            String listSemester = semesterBs.getListSemester(strCourseId);
            out.printf(listSemester);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
//</editor-fold>

}
