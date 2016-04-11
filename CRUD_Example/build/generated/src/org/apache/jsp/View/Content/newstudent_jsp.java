package org.apache.jsp.View.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DTO.User;
import DAO.CourseDao;
import DTO.Course;
import DTO.ClassStudy;
import java.util.List;
import DAO.ClassStudyDao;

public final class newstudent_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Index</title>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            function myFunction(){ \n");
      out.write("            var name=document.getElementById('Name');\n");
      out.write("            var birthday = document.getElementById('Birthday');\n");
      out.write("            var address = document.getElementById('Address');\n");
      out.write("            \n");
      out.write("            if (name.value == \"\" || birthday.value == \"\" || address.value == \"\"){  \n");
      out.write("              alert(\"Please fill all textbox\");  \n");
      out.write("              return false;  \n");
      out.write("            }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 
            HttpSession sesions = request.getSession();
            ClassStudyDao classDao = new ClassStudyDao();
            CourseDao courseDao = new CourseDao();
            String strCourseId = request.getParameter("courseid");
            
            if(strCourseId == null){
                strCourseId = "1";
            }
            User user = (User)session.getAttribute("user");
            if(user == null){
                sesions.setAttribute("url", request.getRequestURI());
                response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
            }
            List<Course> listCourse = courseDao.getListCourse();
            //Get list class
            List<ClassStudy> listClass = classDao.getListClass(strCourseId);
        
      out.write("\n");
      out.write("         ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/header.jsp", out, false);
      out.write("\n");
      out.write("         <div class=\"container div-content\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-md-3 menu_left\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/menu_left.jsp", out, false);
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-9 \">\n");
      out.write("                   <div class=\"row content-right\">\n");
      out.write("                        <div class=\"col-sm-12 col-md-12 col-lg-12\">\n");
      out.write("                            <div class=\"row\">\n");
      out.write("                                <div class=\"col-sm-12 col-md-12 col-lg-12\">\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-sm-12 col-md-12 col-lg-12 list-student\">\n");
      out.write("                                            <div class=\"row\">\n");
      out.write("                                                <form name =\" myform\" onsubmit=\"return myFunction();\" action = \"../../addNewStudent\" method=\"post\"  />\n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Name: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <input type=\"text\" class = \"form-control\" placeholder =\"Name\" name = \"Name\" id=\"Name\"/>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Birthday: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <input type=\"text\" class = \"form-control\" placeholder =\"2016-09-15\" name = \"Birthday\" id=\"Birthday\"/>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Gender </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <select class = \"form-control\" name = \"Gender\" id=\"Gender\">\n");
      out.write("                                                                   <option value = \"Male\">Male</option>\n");
      out.write("                                                                    <option value = \"Female\">Female</option>\n");
      out.write("                                                                </select>\n");
      out.write("                                                             </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div> \n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Address: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <input type=\"text\" class = \"form-control\" placeholder = \"Address\" name = \"Address\" id = \"Address\"/>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div> \n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Course: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <select class = \"form-control\" name = \"CourseId\" id = \"course\" >\n");
      out.write("                                                                   ");

                                                                       for(int i = 0; i < listCourse.size(); i++){ 
                                                                            if(strCourseId.equals(String.valueOf(listCourse.get(i).getId()))){
                                                                    
      out.write("\n");
      out.write("\n");
      out.write("                                                                                <option value = \"");
      out.print(listCourse.get(i).getId() );
      out.write("\" selected=\"selected\">\n");
      out.write("                                                                                    ");
      out.print( listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() );
      out.write("\n");
      out.write("                                                                                </option>    \n");
      out.write("\n");
      out.write("                                                                    ");
 
                                                                            } else {
                                                                    
      out.write("              \n");
      out.write("                                                                                <option value = \"");
      out.print(listCourse.get(i).getId() );
      out.write("\">\n");
      out.write("                                                                                    ");
      out.print( listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() );
      out.write("\n");
      out.write("                                                                                </option> \n");
      out.write("\n");
      out.write("                                                                    ");
      
                                                                            }
                                                                        }
                                                                   
      out.write("\n");
      out.write("                                                                </select>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Class: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <select class = \"form-control\" name = \"ClassId\">\n");
      out.write("                                                                    ");

                                                                       for(int i = 0; i < listClass.size(); i++){ 
                                                                    
      out.write("\n");
      out.write("\n");
      out.write("                                                                       <option value = \"");
      out.print(listClass.get(i).getId() );
      out.write('"');
      out.write('>');
      out.print(listClass.get(i).getName());
      out.write("</option>\n");
      out.write("\n");
      out.write("                                                                    ");
   }
                                                                   
      out.write("\n");
      out.write("\n");
      out.write("                                                                </select>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div>\n");
      out.write("                                                        <button type=\"submit\"  class=\"btn div-submit-index\">Add new student</button>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </form>\n");
      out.write("                                            </div>\n");
      out.write("                                            <h3 class=\"text-subframe\"></h3>    \n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>    \n");
      out.write("                            </div>\n");
      out.write("                        </div>    \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("             </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
