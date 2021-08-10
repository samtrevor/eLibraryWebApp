package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import dao.BookDao;

/**
 * Servlet implementation class ViewBook
 */
@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
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
		out.println("<title>Librarian Section</title>");
		out.println("<link rel='stylesheet' type='text/css' href='style-content.css'/>");
		out.println("</head>");
		out.println("<body>");

		request.getRequestDispatcher("LibrarianNav.html").include(request, response);

		List<BookBean> list = BookDao.viewBook();
		
			out.println("<div class='view-book'><table class='view-table'>");
			out.println(
					"<tr><th class='view-th'>CallNo</th><th class='view-th'>Name</th><th class='view-th'>Author</th><th class='view-th'>Publisher</th><th class='view-th'>Quantity</th><th class='view-th'>Issued</th><th class='view-th'>Delete</th></tr>");
			for (BookBean bean : list) {
			out.println("<tr><td class='view-td'>" + bean.getCallno() + "</td><td class='view-td'>" + bean.getName() + "</td><td class='view-td'>"
					+ bean.getAuthor() + "</td><td class='view-td'>" + bean.getPublisher() + "</td><td class='view-td'>" + bean.getQuantity()
					+ "</td><td class='view-td'>" + bean.getIssued() + "</td><td class='view-td'><a href='DeleteBook?callno="+bean.getCallno()+"'>delete</a></td></tr>");
			}
			out.println("</table></div>");
	}

}
