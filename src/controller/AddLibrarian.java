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
 * Servlet implementation class AddLibrarian
 */
@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!Doctype html>");
		out.println("<head>");
		out.println("<title>Admin Section</title>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("adminNav.html").include(request, response);
		
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		
		LibrarianBean bean = new LibrarianBean(id, name, password, email, mobile);
		LibrarianDao dao = new LibrarianDao();
		int status = dao.save(bean);
		if(status>0){
			out.println("<h3>Librarian added successfully</h3>");
			request.getRequestDispatcher("AddLibrarianForm.html").include(request, response);
		}else{
			out.println("<h3>An Error occured! Librarian not saved!</h3>");
			request.getRequestDispatcher("AddLibrarianForm.html").include(request, response);
		}
	}

}
