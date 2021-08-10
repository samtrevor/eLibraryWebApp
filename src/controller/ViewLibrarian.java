package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LibrarianBean;
import dao.LibrarianDao;

/**
 * Servlet implementation class ViewLibrarian
 */
@WebServlet("/ViewLibrarian")
public class ViewLibrarian extends HttpServlet {
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
		out.println("<title>Admin Section</title>");
		out.println("<link rel='stylesheet' type='text/css' href='style-content.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("adminNav.html").include(request, response);

		List<LibrarianBean> list = LibrarianDao.viewlibrarian();

		out.println("<div class='view'><table class='view-table'");

		out.println(
				"<tr><th class='view-th'>ID</th><th class='view-th'>Name</th><th class='view-th'>Password</th><th class='view-th'>Email</th><th class='view-th'>Mobile</th><th class='view-th'/><th class='view-th'/></tr>");

		for (LibrarianBean bean : list) {
			out.println("<tr><td class='view-td'>" + bean.getId() + "</td><td class='view-td'>" + bean.getName()
					+ "</td><td class='view-td'>" + bean.getPassword() + "</td><td class='view-td'>" + bean.getEmail()
					+ "</td><td class='view-td'>" + bean.getMobile()
					+ "</td><td class='view-td'><a href='EditLibrarianForm?id=" + bean.getId()
					+ "'>Edit</a><td class='view-td'><a href='DeleteLibrarian?id=" + bean.getId()
					+ "'>Delete</a></td</tr>");
		}

		out.println("</table></div>");

	}

}
