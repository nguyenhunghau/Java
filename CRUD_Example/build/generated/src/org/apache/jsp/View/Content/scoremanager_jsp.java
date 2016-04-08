package org.apache.jsp.View.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DAO.ScoreDao;
import DTO.Course;
import DAO.CourseDao;
import DTO.Score;
import DTO.Semester;
import java.util.List;
import DTO.Student;
import DAO.StudentDao;
import DAO.SemesterDao;

public final class scoremanager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            SemesterDao semesterDao = new SemesterDao();
            CourseDao courseDao = new CourseDao();
            StudentDao studentDao = new StudentDao();
            ScoreDao scoreDao = new ScoreDao();
            HttpSession sessions = request.getSession();
            List<Score> listScore = (List<Score>)sessions.getAttribute("listScore");
            //Get list Course
            List<Course> listCourse = courseDao.getListCourse();
            //Get course id
            String strCourseId = request.getParameter("courseid");
            //Get studentId
            String strStudentId = request.getParameter("StudentId");
            //Get SemesterId
            String strSemesterId = request.getParameter("SemesterId");
            String strAction = request.getParameter("act");
            //delete score
            if(strAction != null){
                if(strAction.equals("delete")){
                    String strId = request.getParameter("ID");
                    strStudentId = scoreDao.getScore(strId).getStudentId();
                    scoreDao.deleteScore(strId);
                }
            }
            //Create session with current student
            sessions.setAttribute("student", strStudentId);
            if(strCourseId == null){
                strCourseId = "1";
            }
            if(listScore == null)
                listScore = scoreDao.getListScore(strStudentId, "");
            List<Semester> listSemester = semesterDao.getListSemester(strCourseId);
            String strMessage = (String)sessions.getAttribute("message");
        
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/header.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container div-content\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-3 menu_left\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/menu_left.jsp", out, false);
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-9 \">\n");
      out.write("                    <!--content_right-->\n");
      out.write("                    <div class=\"row content-right\">\n");
      out.write("                        <div class=\"col-sm-12 col-md-12 col-lg-12\">\n");
      out.write("                            <div class=\"row\">\n");
      out.write("                                <div class=\"col-sm-12 col-md-12 col-lg-12\">\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-sm-12 col-md-12 col-lg-12 list-student\">\n");
      out.write("                                            <div class=\"row\">\n");
      out.write("                                                <form action = \"../../getListScore \" method=\"post\">\n");
      out.write("                                                    <div class=\"form-group col-md-12\">\n");
      out.write("                                                        <label class=\"col-md-12\" >");
      out.print(strMessage );
      out.write(" </label>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"form-group col-md-12\">\n");
      out.write("                                                        <label class=\"col-md-12\" ></label>\n");
      out.write("                                                        <input type=\"hidden\" name = \"StudentId\" value=\"");
      out.print( strStudentId );
      out.write("\"/>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"form-group col-md-6\">\n");
      out.write("                                                        <label class=\"col-md-3\" >Course: </label>\n");
      out.write("                                                        <div class=\"col-md-9\">\n");
      out.write("                                                            <select class = \"form-control\" name = \"CourseId\" id = \"course\" >\n");
      out.write("                                                               ");

                                                                   for(int i = 0; i < listCourse.size(); i++){ 
                                                                        if(strCourseId.equals(String.valueOf(listCourse.get(i).getId()))){
                                                                
      out.write("\n");
      out.write("                                                                           \n");
      out.write("                                                                            <option value = \"");
      out.print(listCourse.get(i).getId() );
      out.write("\" selected=\"selected\">\n");
      out.write("                                                                                ");
      out.print( listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() );
      out.write("\n");
      out.write("                                                                            </option>    \n");
      out.write("                                                               \n");
      out.write("                                                                ");
 
                                                                        } else {
                                                                
      out.write("              \n");
      out.write("                                                                            <option value = \"");
      out.print(listCourse.get(i).getId() );
      out.write("\">\n");
      out.write("                                                                                ");
      out.print( listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() );
      out.write("\n");
      out.write("                                                                            </option> \n");
      out.write("                                                                            \n");
      out.write("                                                                ");
      
                                                                        }
                                                                    }
                                                               
      out.write("\n");
      out.write("                                                            </select>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("\n");
      out.write("                                                    <div class=\"form-group col-md-6\">\n");
      out.write("                                                        <label class=\"col-md-3\" >Semester </label>\n");
      out.write("                                                        <div class=\"col-md-9\">\n");
      out.write("                                                            <select class = \"form-control\" name = \"SemesterId\">\n");
      out.write("                                                                ");

                                                                   for(int i = 0; i < listSemester.size(); i++){ 
                                                                
      out.write("\n");
      out.write("                                                                   \n");
      out.write("                                                                   <option value = \"");
      out.print(listSemester.get(i).getId() );
      out.write('"');
      out.write('>');
      out.print(listSemester.get(i).getName());
      out.write("</option>\n");
      out.write("                                                                \n");
      out.write("                                                                ");
   }
                                                               
      out.write("\n");
      out.write("                                                               \n");
      out.write("                                                            </select>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div >\n");
      out.write("                                                        <button type=\"submit\"  class=\"btn div-submit-index\">Search</button>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </form>\n");
      out.write("                                            </div>\n");
      out.write("                                           \n");
      out.write("                                            <div class = \"row\" style=\"overflow: hidden;\">\n");
      out.write("                                                \n");
      out.write("                                                <table class=\"tbl_border\" cellspacing=\"0px\" cellpadding=\"5px\" rules=\"all\" border=\"1\"\n");
      out.write("                                                    style=\"border-collapse: collapse;\" width=\"100%\" id=\"tblshow\">\n");
      out.write("                                                    <thead>\n");
      out.write("                                                          <tr>\n");
      out.write("                                                        <td class=\"headertable\" style=\"width: 10px;\">\n");
      out.write("                                                            No\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Semester\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Subject\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Score 1\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Score 2\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Score 3\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Average\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                           Edit\n");
      out.write("                                                        </td >\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Delete\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    </thead>\n");
      out.write("                                                  \n");
      out.write("                                                    ");
 
                                                        if(listScore != null)
                                                        {
                                                            for(int i =0; i< listScore.size(); i++){
                                                                int no = i + 1;
                                                    
      out.write("\n");
      out.write("                                                            <tr>\n");
      out.write("                                                                <td align = \"center\">\n");
      out.write("                                                                    ");
      out.print( no);
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"center\">\n");
      out.write("                                                                    ");
      out.print( listScore.get(i).getStrNameSemester());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listScore.get(i).getNameSubject());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"right\">\n");
      out.write("                                                                    ");
      out.print( listScore.get(i).getScrore_1());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"right\">\n");
      out.write("                                                                    ");
      out.print( listScore.get(i).getScrore_2());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"right\">\n");
      out.write("                                                                   ");
      out.print( listScore.get(i).getScrore_3());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"right\">\n");
      out.write("                                                                    ");
      out.print( listScore.get(i).getAverage());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                 <td align = \"center\">\n");
      out.write("                                                                     <a href=\"");
      out.print( "updatescore.jsp?ID=" + listScore.get(i).getId() );
      out.write("\" >\n");
      out.write("                                                                         <img src=\"../../img/images/Edit.png\" class = \"img-edit\" alt=\"\"/>\n");
      out.write("                                                                     </a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"center\">\n");
      out.write("                                                                     <a href=\"");
      out.print( "scoremanager.jsp?act=delete&&ID=" + listScore.get(i).getId());
      out.write("\" >\n");
      out.write("                                                                         <img src=\"../../img/images/delete.png\" class = \"img-edit\" alt=\"\" onclick=\"return confirm('Are you sure?')\"/>\n");
      out.write("                                                                     </a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                            </tr>   \n");
      out.write("                                                    ");
} }
      out.write("\n");
      out.write("                                                </table>\n");
      out.write("                                            </div>\n");
      out.write("                                                \n");
      out.write("                                                <div class=\"row\" style=\"margin-top:10px;margin-left: 20px;\">\n");
      out.write("                                                    <a href=\"");
      out.print("/CRUD_Example/faces/View/Content/newscore.jsp?SemesterId=" + strSemesterId );
      out.write("\" >Add new score</a>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("            </div>\n");
      out.write("        </div><!--kt_content right-->\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            // bind change event to select\n");
      out.write("             $('#course').bind('change', function () {\n");
      out.write("                var url = this.value; // get selected value\n");
      out.write("                if (url!== '') { // require a URL\n");
      out.write("                    window.location = 'scoremanager.jsp?courseid=' + url; // redirect\n");
      out.write("                }\n");
      out.write("                return false;\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    \n");
      out.write("    </body>\n");
      out.write("</html\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
