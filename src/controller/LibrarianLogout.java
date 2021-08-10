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
 * Servlet implementation class LibrarianLogout
 */
@WebServlet("/LibrarianLogout")
public class LibrarianLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!Doctype html>");
		out.println("<head>");
		out.println("<title>eLibrary</title>");
		out.println("</head>");
		out.println("<body>");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

}
