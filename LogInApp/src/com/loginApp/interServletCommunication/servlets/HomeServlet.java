package com.loginApp.interServletCommunication.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(urlPatterns="/homeServlet",loadOnStartup=2)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public void init() {
    	   System.out.println("checking the pre-initialising of the servlet.");
       }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=(String)request.getAttribute("message");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.print(message);
	}


	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
