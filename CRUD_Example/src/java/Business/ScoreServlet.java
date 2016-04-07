/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.ScoreDao;
import DAO.StudentDao;
import DTO.Score;
import DTO.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//</editor-fold>

/**
 *
 * @author root
 */
public class ScoreServlet extends HttpServlet {

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
            out.println("<title>Servlet ScoreServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScoreServlet at " + request.getContextPath() + "</h1>");
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
       
        doPost(request, response);
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
        
        ScoreDao scoreDao = new ScoreDao();
        Score score= new Score();
        HttpSession sessions = request.getSession();
        String path = request.getServletPath();
        String url = "/CRUD_Example/faces/View/Content/scoremanager.jsp";
        String studentId = (String)sessions.getAttribute("student");
        String semesterId = (String)sessions.getAttribute("semesterid");
        if(semesterId == null)
            semesterId = "1";
        String message = "";
        
        try {
            
            switch(path){
                case "/getListScore":
                    url += "?SemesterId=" + semesterId ;
                    //Get score of student in database
                    List<Score> listScore = scoreDao.getListScore(studentId, semesterId);
                    sessions.setAttribute("listScore", listScore);   
                    sessions.setAttribute("semesterid", semesterId);
                    break;
                case "/addNewScore":
                    url += "?SemesterId=" + semesterId + "&&StudentId=" + studentId;
                    score.setStudentId(studentId);
                    score.setSemesterId(Integer.valueOf(semesterId));
                    score.setSubjectId(Integer.valueOf(request.getParameter("Subject")));
                    score.setScrore_1(Float.valueOf(request.getParameter("Score1")));
                    score.setScrore_2(Float.valueOf(request.getParameter("Score2")));
                    score.setScrore_3(Float.valueOf(request.getParameter("Score3")));
                    
                    if(scoreDao.addNewScore(score))
                        message = "Add score sucessful";
                    else
                        message = "Can not add score";
                    
                case "/updateScore":
                    score.setStudentId(studentId);
                    score.setSemesterId(Integer.valueOf(semesterId));
                    score.setSubjectId(Integer.valueOf(request.getParameter("Subject")));
                    score.setScrore_1(Float.valueOf(request.getParameter("Score1")));
                    score.setScrore_2(Float.valueOf(request.getParameter("Score2")));
                    score.setScrore_3(Float.valueOf(request.getParameter("Score3")));
                    
                    if(scoreDao.updateScore(score))
                        message = "Update score sucessful";
                    else
                        message = "Can not update score";
                default:
                    break;
            }
            
           
            //request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
              ex.printStackTrace();
        } finally{
             sessions.setAttribute("message", message);
            //Redirect to scoremanager.jsp
            response.sendRedirect(url); 
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
