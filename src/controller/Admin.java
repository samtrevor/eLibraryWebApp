package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
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
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		if((email.equals("samtrevor13@gmail.com")) && (pass.equals("admin123"))){
			HttpSession session = request.getSession();
			session.setAttribute("admin", email);
			
			request.getRequestDispatcher("adminNav.html").include(request, response);
		//	request.getRequestDispatcher("adminCorousel.html").include(request, response);
			
		}else{
			request.getRequestDispatcher("header.html").include(request, response);
			out.println("<h3 style='color:white; font-size:bold; margin-top:80px; margin-left:12px;'><b>Username or Password error!</b></h3>");
			request.getRequestDispatcher("adminLoginForm.html").include(request, response);
		}
		
		
		
	}

}
