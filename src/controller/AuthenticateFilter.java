package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticateFilter
 */
@WebFilter("/AuthenticateFilter")
public class AuthenticateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticateFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getServletPath();
		//HttpSession session = req.getSession(false);
		if(url.equals("index.html")){
//			session = req.getSession(true);
//			session.setAttribute("url", url);
			
			chain.doFilter(request, response);
			return;
		}else if(url.equals("/Admin")){
			chain.doFilter(request, response);
			return;
		}else if(url.equals("/LibrarianLogin")){
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession sess = req.getSession();
		String admin = (String) sess.getAttribute("Admin");
		String librarian =(String) sess.getAttribute("Librarian");
		if(admin!=null){
			chain.doFilter(request, response);
			return;
		}else if(librarian != null){
			chain.doFilter(request, response);
			return;
		}
	
		req.getRequestDispatcher("index.html").forward(request, response);
		

		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
