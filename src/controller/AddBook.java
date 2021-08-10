package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import dao.BookDao;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!Doctype html>");
		out.println("<head>");
		out.println("<title>Librarian Section</title>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("LibrarianNav.html").include(request, response);
		
		String callno = request.getParameter("callno");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String squantity = request.getParameter("quantity");
		int quantity=Integer.parseInt(squantity);
		
		BookBean bean = new BookBean(callno, name, author, publisher, quantity);
		int status = BookDao.save(bean);
		if(status>0){
			out.println("<h3>Book Saved Successfully</h3>");
			request.getRequestDispatcher("AddBookForm.html").include(request, response);
		}else{
			out.println("<h3>Book not Saved! An Error occured!</h3>");
			request.getRequestDispatcher("AddBookForm.html").include(request, response);
		}
	}

}
