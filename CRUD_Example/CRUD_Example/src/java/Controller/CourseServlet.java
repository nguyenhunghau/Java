package Controller;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Business.CourseBS;
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
public class CourseServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        CourseBS courseBS = new CourseBS();
        
        try {
             String listCourse = courseBS.getListCourse();
             out.printf(listCourse);
             
        } catch(Exception e) {
            
        }
    }
// </editor-fold>

}
