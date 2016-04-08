package org.apache.jsp.View.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DTO.Course;
import java.util.List;
import DAO.CourseDao;
import DAO.CourseDao;

public final class updateclass_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("      <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/header.jsp", out, false);
      out.write("\n");
      out.write("        ");
 
            CourseDao courseDao = new CourseDao();
            List<Course> listCourse = courseDao.getListCourse();
            String strClassId = request.getParameter("ID");
            
        
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
      out.write("                                                <form name =\" myform\" onsubmit=\"return myFunction();\" action = \"../../updateClass\" method=\"post\"  />\n");
      out.write("                                                    <input type=\"hidden\" class = \"form-control\" id=\"name\" name = \"ID\" value = ");
      out.print(strClassId );
      out.write("/>\n");
      out.write("                                                            \n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Name: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <input type=\"text\" class = \"form-control\" placeholder =\"Name\" name = \"Name\" id=\"Name\"/>\n");
      out.write("                                                            </div>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    \n");
      out.write("                                                    <div class=\"row\">\n");
      out.write("                                                        <div class=\"form-group col-md-12\">\n");
      out.write("                                                            <label class=\"col-md-3\" >Course: </label>\n");
      out.write("                                                            <div class=\"col-md-9\">\n");
      out.write("                                                                <select class = \"form-control\" name = \"CourseId\">\n");
      out.write("                                                                    ");

                                                                       for(int i = 0; i < listCourse.size(); i++){ 
                                                                    
      out.write("\n");
      out.write("\n");
      out.write("                                                                       <option value = \"");
      out.print(listCourse.get(i).getId() );
      out.write('"');
      out.write('>');
      out.print(listCourse.get(i).getYearBegin() + " - " + listCourse.get(i).getYearEnd() );
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
      out.write("                                                        <button type=\"submit\"  class=\"btn div-submit-index\">Update class</button>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </form>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>  \n");
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
