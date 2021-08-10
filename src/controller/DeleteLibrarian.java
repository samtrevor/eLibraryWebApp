package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibrarianDao;

/**
 * Servlet implementation class DeleteLibrarianForm
 */
@WebServlet("/DeleteLibrarian")
public class DeleteLibrarian extends HttpServlet {
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
		LibrarianDao.delete(id);
		response.sendRedirect("ViewLibrarian");
	}

}
