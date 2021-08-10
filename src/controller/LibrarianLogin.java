package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibrarianDao;

/**
 * Servlet implementation class LibrarianLogin
 */
@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
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
		
		String email = request.getParameter("libEmail");
		String password = request.getParameter("libPassword");
		
		LibrarianDao dao = new LibrarianDao();
		boolean status = dao.validate(email, password);
		if(status){
			HttpSession session = request.getSession();
			session.setAttribute("Librarian", email);
			
			request.getRequestDispatcher("LibrarianNav.html").include(request, response);
		}else{
			request.getRequestDispatcher("header.html").include(request, response);
			out.println("<h3 style='color:white; font-size:bold; margin-top:80px; margin-left:12px;'>Sorry username or password error!</h3>");
			request.getRequestDispatcher("librarianLoginForm.html").include(request, response);
		}
	}

}
