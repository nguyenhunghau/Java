package Faber.Servlet;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Business.HandleLocation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//</editor-fold>

/**
 *
 * @author Nguyen Hung Hau
 */
public class HandleLocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HandleLocation handleLocation = new HandleLocation();
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("account");

        //<editor-fold defaultstate="collapsed" desc="SHOW CAPTURE GOOGLE SEARCH">
        if (userPath.equals("/showCapture")) {
            String urlImage = request.getParameter("image");
            String destinationFile = "/usr/local/follow-website/" + username + "/google-search/" + urlImage;
            handleLocation.showCapture(destinationFile, response);
        }
        //</editor-fold>
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleLocation handleLocation = new HandleLocation();
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("account");

        //<editor-fold defaultstate="collapsed" desc="GET LIST LOCATION">
        if (userPath.equals("/getListLocation")) {
            PrintWriter out = response.getWriter();
            out.print(handleLocation.getCanonicalName());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="SEARCH GOOGLE">
        if (userPath.equals("/searchGoogle")) {
            PrintWriter out = response.getWriter();
            String location = request.getParameter("location");
            String keyword = request.getParameter("keyword");
            String result = handleLocation.captureGoogleSearch(keyword, location, username);
            out.print(result);
        }
        //</editor-fold>

    }
}
