
package Business;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.ScoreDao;
import DAO.StudentDao;
import DAO.SubjectDao;
import DTO.Score;
import DTO.Student;
import DTO.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
public class StudentServlet extends HttpServlet {

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
            out.println("<title>Servlet StudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        
        HttpSession session = request.getSession();
       
        //Set login
        if(session.getAttribute("user") == null)
            response.sendRedirect("/CRUD_Example/login.jsp");
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ScoreDao scoreDao = new ScoreDao();
        Student student = new Student();
        java.util.Date date;
        String strUrl = "/CRUD_Example/studentmanager.jsp";
        int message = 0;
        //Get server path to handle event
        String strPath = request.getServletPath();
        //Get student id when update
        String strStudentId = request.getParameter("ID");
        String strName = request.getParameter("Name");
        String strAddress = request.getParameter("Address");
        if(strName != null )
             strName = URLDecoder.decode(strName, "UTF-8");;
        if(strAddress != null )
             strAddress = URLDecoder.decode(strAddress, "UTF-8");
        
        //Format time for mysql
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date receive = Calendar.getInstance().getTime();
        
        try {
            switch(strPath) {
                    
                case "/updateStudent":
                    // Get data from inputs in form in file newstudent.jsp
                    student.setId(strStudentId);
                    student.setAddress(strAddress);
                    date = format.parse(request.getParameter("Birthday")); 
                    student.setBirthday(new Date(date.getTime()));
                    student.setGender(request.getParameter("Gender"));
                    student.setName(strName);
                    student.setReceiveDay(new Date(receive.getTime()));
                    student.setClassId(Integer.valueOf(request.getParameter("ClassId")));
                    //uppdate student into database
                    if(studentDao.updateStudent(student))
                        message = 1;
                    else
                        message = 2;
                    
                    break;
                    
                case "/addNewStudent":
                    student.setId(studentDao.createId());
                    student.setAddress(strAddress);
                    date = format.parse(request.getParameter("Birthday")); 
                    student.setBirthday(new Date(date.getTime()));
                    student.setGender(request.getParameter("Gender"));
                    student.setName(strName);
                    student.setReceiveDay(new Date(receive.getTime()));
                    student.setClassId(Integer.valueOf(request.getParameter("ClassId")));
                    //insert student into database
                    if(studentDao.addNewStudent(student)){
                        message = 3;
                        //<editor-fold defaultstate="collapsed" desc="Add score for this student in this semester">
                        //Get list subject
                        List<Subject> listSubject = subjectDao.getListSubject();
                        for(Subject subject : listSubject){
                            Score score = new Score();
                            score.setStudentId(student.getId());
                            score.setSemesterId(1);
                            score.setSubjectId(subject.getId());
                            score.setScrore_1(-1);
                            score.setScrore_2(-1);
                            score.setScrore_3(-1);
                            
                            scoreDao.addNewScore(score);
                        }
//</editor-fold>
                        
                    }
                    else
                        message = 4;

                    break;
                default:
                    break;
                        
            }
            
           
        } catch (Exception ex) {
              ex.printStackTrace();
        }  finally {
            response.sendRedirect(strUrl += "?message=" + message);
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
