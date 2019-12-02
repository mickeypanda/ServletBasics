package com.webapp.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Connection conn;   
    private Statement stmt;

	
	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(config.getInitParameter("dbUrl"),config.getInitParameter("dbUser"),config.getInitParameter("dbPassword"));
			stmt=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		int count;
		String sqlQuery="insert into user values('"+firstname+"','"+lastname+"','"+email+"','"+password+"')";
		try {
			count=stmt.executeUpdate(sqlQuery);
			PrintWriter out=response.getWriter();
			if(count>0) {
				out.print("<html><body><h2>User Added Successfully</h2></body></html>");
			}
			else {
				out.print("<html><body><h2>Error adding the user</h2></body></html>");
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
