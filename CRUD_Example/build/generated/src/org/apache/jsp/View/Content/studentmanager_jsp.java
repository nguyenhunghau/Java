package org.apache.jsp.View.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DAO.StudentDao;
import java.util.AbstractList;
import java.util.List;
import DTO.Student;

public final class studentmanager_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Student manager</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Share/header.jsp", out, false);
      out.write("\n");
      out.write("        ");

            List<Student> listStudent = null;
            HttpSession sessions = request.getSession();
            StudentDao studenDao = new StudentDao();
            // Get id of student we want to delete
            String id = request.getParameter("ID");
            // Get type of action such as update, add new student...
            String type = request.getParameter("type");
            
            if(id != null){
                studenDao.deleteStudent(id);
            }
             
            // If the first pageload or then add new student or then update, get all student
            if(type == null || type.equals("update") || type.equals("addnew")){
                listStudent = studenDao.getListStudent("", "");
            } else {
                listStudent = (List<Student>)sessions.getAttribute("listStudent");
            }
           
            String message = (String)sessions.getAttribute("message");
            if(message == null)
                message = "";
        
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
      out.write("                                                <form action = \"../../getListStudent\" method=\"post\">\n");
      out.write("                                                    <div class=\"form-group col-md-12\">\n");
      out.write("                                                        <label class=\"col-md-12\" >");
      out.print(message );
      out.write(" </label>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"form-group col-md-6\">\n");
      out.write("                                                        <label class=\"col-md-3\" >ID: </label>\n");
      out.write("                                                        <div class=\"col-md-9\">\n");
      out.write("                                                            <input type=\"text\" class = \"form-control\" name = \"ID\"/>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("\n");
      out.write("                                                    <div class=\"form-group col-md-6\">\n");
      out.write("                                                        <label class=\"col-md-3\" >Name: </label>\n");
      out.write("                                                        <div class=\"col-md-9\">\n");
      out.write("                                                            <input type=\"text\" class = \"form-control\" name = \"name\"/>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div >\n");
      out.write("                                                        <button type=\"submit\"  class=\"btn div-submit-index\">Search</button>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </form>\n");
      out.write("                                            </div>\n");
      out.write("                                            ");
 
                                                    if(listStudent != null){
                                            
      out.write("\n");
      out.write("                                            \n");
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
      out.write("                                                            ID\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Name\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Birthday\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Gender\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Address\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                           Update\n");
      out.write("                                                        </td >\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            Delete\n");
      out.write("                                                        </td>\n");
      out.write("                                                        <td class=\"headertable\">\n");
      out.write("                                                            View score\n");
      out.write("                                                        </td>\n");
      out.write("                                                    </tr>\n");
      out.write("                                                    </thead>\n");
      out.write("                                                  \n");
      out.write("                                                    ");
 
                                                        
                                                            for(int i =0; i< listStudent.size(); i++){
                                                                int no = i + 1;
                                                    
      out.write("\n");
      out.write("                                                            <tr>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( no);
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listStudent.get(i).getId() );
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listStudent.get(i).getName());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listStudent.get(i).getBirthday());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listStudent.get(i).getGender());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    ");
      out.print( listStudent.get(i).getAddress());
      out.write("\n");
      out.write("                                                                </td>\n");
      out.write("                                                                 <td>\n");
      out.write("                                                                     <a href=");
      out.print( "updatestudent.jsp?ID=" + listStudent.get(i).getId() );
      out.write("><img src=\"../../img/images/Edit.png\" class = \"img-edit\" title=\"Edit\" alt=\"\"/></a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    <a href=");
      out.print( "scoremanager.jsp?ID=" + listStudent.get(i).getId() );
      out.write("><img src=\"../../img/images/delete.png\" class = \"img-edit\" title =\"Delete\"/></a>\n");
      out.write("                                                                </td>\n");
      out.write("                                                                <td>\n");
      out.write("                                                                    <a href= ");
      out.print( "scoremanager.jsp?StudentId=" + listStudent.get(i).getId() );
      out.write(" ><img src=\"../../img/score.png\" class = \"img-edit\" title=\"View score\" alt=\"\"/></a>\n");
      out.write("                                                                \n");
      out.write("                                                                </td>\n");
      out.write("                                                            </tr>   \n");
      out.write("                                                    ");
} }
      out.write("\n");
      out.write("                                                </table>\n");
      out.write("                                            </div>\n");
      out.write("                                                \n");
      out.write("                                                <div class=\"row\" style=\"margin-top:10px;margin-left: 20px;\">\n");
      out.write("                                                <a href=\"/CRUD_Example/faces/View/Content/newstudent.jsp\">Create new student</a>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
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
