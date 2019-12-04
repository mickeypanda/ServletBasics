package com.mickey.servletFilters.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FilterDemoServlet
 */
@WebServlet("/FilterDemoServlet")
public class FilterDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init() {
    	System.out.println("from servlet init() method.");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("from the server");
	}

}
