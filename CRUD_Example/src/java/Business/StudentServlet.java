
package Business;
//<editor-fold defaultstate="collapsed" desc="IMPORT">
import DAO.StudentDao;
import DTO.Student;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        String strMessage = "";
        java.util.Date date;
        String url = "/CRUD_Example/faces/View/Content/studentmanager.jsp";
        HttpSession session = request.getSession();
        //Get server path to handle event
        String path = request.getServletPath();
        //Format time for mysql
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date receive = Calendar.getInstance().getTime();
        
        try {
            switch(path) {
                case "getListStudent":
                     // Get Id and Name from input in form in file studentmanager.jsp
                    String Id = request.getParameter("ID");
                    String Name = request.getParameter("name");
                    url += "?type=search";
                    //Get student in database
                    List<Student> listStudent = studentDao.getListStudent(Id, Name);
                    session.setAttribute("listStudent", listStudent);

                    break;
                    
                case "updateStudent":
                    // Get data from inputs in form in file newstudent.jsp
                    student.setId(studentDao.createId());
                    student.setAddress(request.getParameter("Address"));
                    date = format.parse(request.getParameter("Birthday")); 
                    student.setBirthday(new Date(date.getTime()));
                    student.setGender(request.getParameter("Gender"));
                    student.setName(request.getParameter("Name"));
                    student.setReceiveDay(new Date(receive.getTime()));
                    //uppdate student into database
                    if(studentDao.updateStudent(student))
                        strMessage = "Update student sucessfully";
                    else
                        strMessage = "Can not update this student";
                    
                    break;
                case "addNewStudent":
                    student.setId(studentDao.createId());
                    student.setAddress(request.getParameter("Address"));
                    date = format.parse(request.getParameter("Birthday")); 
                    student.setBirthday(new Date(date.getTime()));
                    student.setGender(request.getParameter("Gender"));
                    student.setName(request.getParameter("Name"));
                    student.setReceiveDay(new Date(receive.getTime()));
                    //insert student into database
                    if(studentDao.addNewStudent(student))
                        strMessage = "Add student sucessfully";
                    else
                        strMessage = "Can not insert into database";

                    break;
                default:
                    break;
                        
            }
             //send a message 
            session.setAttribute("message", strMessage);
            response.sendRedirect(url);
           
        } catch (Exception ex) {
              ex.printStackTrace();
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
