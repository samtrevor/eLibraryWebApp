package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.IssueBookBean;
import dao.BookDao;

/**
 * Servlet implementation class IssueBook
 */
@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Librarian Section</title>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("LibrarianNav.html").include(request, response);
		
		String callNo = request.getParameter("callNo");
		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String studentMobile = request.getParameter("studentMobile");
		
		IssueBookBean bean = new IssueBookBean(callNo, studentId, studentName, studentMobile);
		int status = BookDao.issueBook(bean);
		if(status>0){
			out.println("Book Issued successfully");
			request.getRequestDispatcher("IssueBookForm.html").include(request, response);
		}else{
			out.println("An Error Occured!");
			request.getRequestDispatcher("IssueBookForm").forward(request, response);
		}
		
	}

}
