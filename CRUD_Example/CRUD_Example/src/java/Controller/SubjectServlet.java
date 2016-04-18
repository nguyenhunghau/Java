package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.SubjectBS;
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
public class SubjectServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="post function">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        SubjectBS subjectBs = new SubjectBS();

        try {
            String listSubject = subjectBs.getListSubject();
            out.printf(listSubject);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

//</editor-fold>
}
