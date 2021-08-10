package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
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
		String studentid = request.getParameter("studentid");
		
		int status = BookDao.returnBook(callno, studentid);
		if(status>0){
			out.println("<h2>Book returned successfully</h2>");
		}else{
			out.println("An Error Occured!");
			request.getRequestDispatcher("ReturnBookForm.html").forward(request, response);
		}
	}

}
