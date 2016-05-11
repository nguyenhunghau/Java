package Faber.Servlet;

import Faber.Business.HanldeUrl;
import Faber.Business.ParseCss;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Hung Hau
 */
public class HandleUrlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HanldeUrl handleUrl = new HanldeUrl();
        PrintWriter out = response.getWriter();
        String path = request.getServletPath();
        String url = request.getParameter("url");
        String result = "";

        if (path.equals("/getContentWebsite")) {
            result = handleUrl.saveWebsite(url, true);
            out.print(result);
        }

        if (path.equals("/browseHistoryWebsite")) {
            url = request.getParameter("url");
            url = handleUrl.getListWebsite(url);
            out.print(url);
        }

        if (path.equals("/checkUrl")) {
            out.print(handleUrl.checkUrl(url));
        }

    }

}
