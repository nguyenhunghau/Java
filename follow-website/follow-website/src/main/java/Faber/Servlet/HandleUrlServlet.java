package Faber.Servlet;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Business.HanldeUrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HandleUrlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HanldeUrl handleUrl = new HanldeUrl();
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String path = request.getServletPath();
        String url = request.getParameter("url");
        String result = ""; 
        if(session.getAttribute("account") == null)
            response.sendRedirect("login.htm");
        String user = (String)session.getAttribute("account") ;
        try {
            if (path.equals("/viewHistory")) {
                result = handleUrl.getListWebsite(url, user);
                out.print(result);
            }
            
            if (path.equals("/getContentWebsite")) {
                String type = url.substring(url.indexOf("#*type=") + 7);
                String urlReal = handleUrl.formatUrl(url.substring(0, url.indexOf("#*type=")));
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                java.sql.Date dateSave = new java.sql.Date(cal.getTime().getTime());
                result = handleUrl.checkUrl(urlReal, dateSave, user);
                if (result.equals("false")) {
                    result = handleUrl.saveWebsite(urlReal, type, user);
                    handleUrl.createCronjob(urlReal, type, user);
                } 
                out.print(result);
            }

            if (path.equals("/changeSchedule")) {
                String time = url.substring(url.lastIndexOf("#*time=") + 7);
                url = handleUrl.formatUrl(url.substring(0, url.lastIndexOf("#*time=")));
                handleUrl.updateCronjob(url, time, user);
            }

            if (path.equals("/checkUrl")) {
                out.print(handleUrl.checkUrl(url, null, user));
            }

            if (path.equals("/deleteUrl")) {
                String time = url.substring(url.lastIndexOf("#*time=") + 7);
                url = handleUrl.formatUrl(url.substring(0, url.lastIndexOf("#*time=")));
                out.print(handleUrl.deleteUrl(url, time, user));
            }
        } catch (Exception ex) {
            Logger.getLogger(HandleUrlServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
