package com.loginApp.interServletCommunication.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private Statement stmt;
	private RequestDispatcher rd;
	
	public void init() {
		ServletContext ctx=getServletContext();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(ctx.getInitParameter("dbUrl"),ctx.getInitParameter("dbUser"),ctx.getInitParameter("dbPassword"));
			stmt=conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			ResultSet rs = stmt.executeQuery("select * from user where email='"+email+"' and password='"+password+"'");
			if(rs.next()) {
				String name=rs.getString("firstname");
				request.setAttribute("message", "Welcome to interservlet communication, Mr."+name);
				rd=request.getRequestDispatcher("homeServlet");
				rd.forward(request, response);
			}else {
				rd=request.getRequestDispatcher("logIn.html");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
