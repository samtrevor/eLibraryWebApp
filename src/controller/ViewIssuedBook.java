package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.IssueBookBean;
import dao.BookDao;

/**
 * Servlet implementation class ViewIssuedBook
 */
@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Librarian Section</title>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("LibrarianNav.html").include(request, response);

		List<IssueBookBean> list = BookDao.viewIssuedBooks();

		out.println("<div class='view-book'><table class='view-table'>");
		out.println(
				"<tr><th class='view-th'>Callno</th><th class='view-th'>Student Id</th><th class='view-th'>Student Name</th><th class='view-th'>Student Mobile</th><th class='view-th'>Issued Date</th><th class='view-th'>Return Status</th></tr>");
		for (IssueBookBean bean : list) {
			out.println("<tr><td class='view-td'>" + bean.getCallNo() + "</td><td class='view-td'>" + bean.getStudentId() + "</td><td class='view-td'>"
					+ bean.getStudentName() + "</td><td class='view-td'>" + bean.getStudentMobile() + "</td><td class='view-td'>" + bean.getIssueDate()
					+ "</td><td class='view-td'>" + bean.getReturnStatus() + "</td></tr>");
		}

		out.println("</table></div>");
	}

}
