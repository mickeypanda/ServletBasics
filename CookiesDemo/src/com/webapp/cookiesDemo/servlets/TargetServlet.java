package com.webapp.cookiesDemo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TargetServlet
 */
@WebServlet("/targetServlet")
public class TargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String userName="";
		System.out.println(cookies.length);
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				System.out.println(cookies[i].getName());
				System.out.println(cookies[i].getValue());
				if(cookies[i].getName().equals("myCookie"))
					{
						userName=cookies[i].getValue();
						cookies[i].setMaxAge(0);
					}
			}
		}
		
		response.setContentType("text/html");
		response.getWriter().print("Welcome to cookies demo, Mr."+userName);
	}

}
