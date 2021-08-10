package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LibrarianBean;
import dao.LibrarianDao;

/**
 * Servlet implementation class EditLibrarianForm
 */
@WebServlet("/EditLibrarianForm")
public class EditLibrarianForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Admin Section</title>");
		out.println("<link rel='stylesheet' type='text/css' href='style-content.css'/>");
		out.println("</head>");
		out.println("</html>");
		
		request.getRequestDispatcher("adminNav.html").include(request, response);
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		
		LibrarianBean bean = LibrarianDao.viewById(id);
		
		out.println("<div class='librarian-div'><form action='EditLibrarian' method='post' class='addlibrarianform'>");
		out.println("<table>");
		out.println("<tr><td><input type='hidden' name='id' value='"+bean.getId()+"'></td></tr>");
		out.println("<tr><td><label>Name</label></td></tr>");
		out.println("<tr><td><input type='text' name='name' value='"+bean.getName()+"'></td></tr>");
		out.println("<tr><td><label>Password</label></td></tr>");
		out.println("<tr><td><input type='password' name='password' value='"+bean.getPassword()+"'></td></tr>");
		out.println("<tr><td><label>Email</label></td></tr>");
		out.println("<tr><td><input type='text' name='email' value='"+bean.getEmail()+"'></td></tr>");
		out.println("<tr><td><label>Mobile</label></td></tr>");
		out.println("<tr><td><input type='text' name='mobile' value='"+bean.getMobile()+"'></td></tr>");
		out.println("<tr><td><input type='submit' value='Submit'></td></tr>");
		out.println("</table>");
		out.println("</form></div>");
	}

}
