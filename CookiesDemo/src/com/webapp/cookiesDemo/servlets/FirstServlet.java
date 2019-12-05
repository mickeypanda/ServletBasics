package com.webapp.cookiesDemo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		//System.out.println(cookies.length);
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				System.out.println(cookies[i].getName());
				System.out.println(cookies[i].getValue());
			}
		}
		String userName=request.getParameter("userName");
		response.addCookie(new Cookie("myCookie",userName));
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<a href='targetServlet'>Click here to see the name.</a>");
	}

}
