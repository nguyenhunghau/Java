package Faber.Servlet;

//<editor-fold defaultstate="collapsed" desc="IMPORT">
import Faber.Business.HanldeUrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.quartz.SchedulerException;
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

        String url = handleUrl.formatUrl(request.getParameter("url"));
        String result = "";
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login.htm");
            return;
        }
        String user = (String) session.getAttribute("account");
        try {

            //<editor-fold defaultstate="collapsed" desc="VIEW HISTORY">
            if (path.equals("/viewHistory")) {
                result = handleUrl.getListWebsite(url, user);
                out.print(result);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="GET CONTENT OF WEBSITE">
            if (path.equals("/getContentWebsite")) {
                String queryString = request.getQueryString();
                Map<String, String> parameter = handleUrl.getParameter(queryString);
                String urlReal = parameter.get("url");
                urlReal = handleUrl.formatUrl(URLDecoder.decode(urlReal, "UTF-8"));
                String type = parameter.get("type");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                java.sql.Date dateSave = new java.sql.Date(cal.getTime().getTime());
                result = handleUrl.checkUrl(urlReal, dateSave, user);
                if (result.equals("false")) {
                    result = handleUrl.saveWebsite(urlReal, type, user);
                }
                out.print(result);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="CHANGE SCHEDULE">
            if (path.equals("/changeSchedule")) {
                String queryString = request.getQueryString();
                Map<String, String> parameter = handleUrl.getParameter(queryString);
                url = parameter.get("url");
                url = handleUrl.formatUrl(URLDecoder.decode(url, "UTF-8"));
                String time = parameter.get("time");
                handleUrl.changeSchedule(url, user, time);
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="CHECK URL">
            if (path.equals("/checkUrl")) {
                out.print(handleUrl.checkUrl(url, user));
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="DELETE URL">
            if (path.equals("/deleteUrl")) {
                String queryString = request.getQueryString();
                Map<String, String> parameter = handleUrl.getParameter(queryString);
                url = parameter.get("url");
                url = handleUrl.formatUrl(URLDecoder.decode(url, "UTF-8"));
                String time = parameter.get("time");
                out.print(handleUrl.deleteUrl(url, time, user));
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="LOAD FILE">
            if (path.equals("/loadFile")) {
                String fileName = "/usr/local/follow-website/" + url;
                handleUrl.loadFile(fileName, response);
            }
            //</editor-fold>

        } catch (SchedulerException | InterruptedException ex) {
            Logger.getLogger(HandleUrlServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
