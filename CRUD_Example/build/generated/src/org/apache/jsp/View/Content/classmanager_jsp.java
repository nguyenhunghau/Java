package org.apache.jsp.View.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DTO.User;
import DTO.ClassStudy;
import java.util.List;
import DAO.ClassStudyDao;

public final class classmanager_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/header.jsp", out, false);
      out.write("\n");
      out.write("        ");

            ClassStudyDao classStudyDao = new ClassStudyDao();
            HttpSession sessions = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user == null){
                sessions.setAttribute("url", request.getRequestURI());
                response.sendRedirect("/CRUD_Example/faces/View/Content/login.jsp");
            }
            //Check ID to delete
            String strId = request.getParameter("ID");
            if(strId != null){
                classStudyDao.deleteClass(Integer.valueOf(strId));
            }
            List<ClassStudy> listClass = classStudyDao.getListClass("");
            String strMessage = (String)sessions.getAttribute("message");
            if(strMessage == null){
                strMessage = "";
            }
        
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
      out.write("                                            <div class=\"form-group col-md-12\">\n");
      out.write("                                                        <label class=\"col-md-12\" >");
      out.print(strMessage );
      out.write(" </label>\n");
      out.write("                                            </div>\n");
      out.write("                                            <div class=\"row\">\n");
      out.write("                                                <h3 style=\"text-align: center;color: red\">Class manager</h3>\n");
      out.write("                                            </div>\n");
      out.write("                                            <div class = \"row\" style=\"overflow: hidden;\">\n");
      out.write("                                                <table class=\"tbl_border\" cellspacing=\"0px\" cellpadding=\"5px\" rules=\"all\" border=\"1\"\n");
      out.write("                                                    style=\"border-collapse: collapse;\" width=\"100%\" id=\"tblshow\">\n");
      out.write("                                                    <thead>\n");
      out.write("                                                          <tr>\n");
      out.write("                                                        <td class=\"headertable\" style=\"width: 10px;\">\n");
      out.write("                                                            No\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Course Id\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Name\n");
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
 
                                                        
                                                            for(int i =0; i< listClass.size(); i++){
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
      out.print( listClass.get(i).getCourseId());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td  align = \"center\">\n");
      out.write("                                                                    ");
      out.print( listClass.get(i).getName());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                               \n");
      out.write("                                                                 <td align = \"center\">\n");
      out.write("                                                                     <a href= \"");
      out.print( "updateclass.jsp?ID=" + listClass.get(i).getId() );
      out.write("\" ><img src=\"../../img/images/Edit.png\" class = \"img-edit\" title=\"Edit\" alt=\"\" /></a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td align = \"center\">\n");
      out.write("                                                                    <a href= \"");
      out.print( "classmanager.jsp?ID=" + listClass.get(i).getId() );
      out.write("\"><img src=\"../../img/images/delete.png\" class = \"img-edit\" title =\"Delete\" onclick=\"return confirm('Are you sure?')\"/></a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                               \n");
      out.write("                                                            </tr>   \n");
      out.write("                                                    ");
} 
      out.write("\n");
      out.write("                                                </table>\n");
      out.write("                                            </div>\n");
      out.write("                                                \n");
      out.write("                                                <div class=\"row\" style=\"margin-top:10px;margin-left: 20px;\">\n");
      out.write("                                                <a href=\"/CRUD_Example/faces/View/Content/newclass.jsp\">Create new class</a>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div><!--kt_content right-->\n");
      out.write("       \n");
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
