/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.ClassStudyDao;
import DTO.ClassStudy;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class ClassServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClassManagerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassManagerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        HttpSession sessions = request.getSession();
        if(sessions.getAttribute("user") == null)
            response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
        ClassStudy classStudy = new ClassStudy();
        ClassStudyDao classDao = new ClassStudyDao();
        String strMessage = "";
        String strUrl = "/CRUD_Example/faces/View/Content/classmanager.jsp";
        String strClassId = request.getParameter("ID");
        
        try {
            switch(path){
                case "/addClass":
                    classStudy.setCourseId(Integer.valueOf(request.getParameter("CourseId")));
                    classStudy.setName(request.getParameter("Name"));
                    if(classDao.addNewClass(classStudy))
                        strMessage = "Add class successful";
                    else
                        strMessage = "Can not insert class";
                    break;
                case "/updateClass":
                    classStudy.setId(Integer.valueOf(strClassId));
                    classStudy.setCourseId(Integer.valueOf(request.getParameter("CourseId")));
                    classStudy.setName(request.getParameter("Name"));
                    if(classDao.updateClass(classStudy))
                        strMessage = "Update class successful";
                    else
                        strMessage = "Can not update class";
                    break;
                default:
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sessions.setAttribute("message", strMessage);
            response.sendRedirect(strUrl);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
