package Faber.Servlet;

import Faber.Business.HandleUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Hung Hau
 */
public class UserServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String json = request.getParameter("json");
        String path = request.getServletPath();
        
        if(path.equals("/checkLogin")) {
            HandleUser handleUser = new HandleUser();
            boolean result = handleUser.checkLogin(json, session);
            out.print(result);
        }
    }
    
}
