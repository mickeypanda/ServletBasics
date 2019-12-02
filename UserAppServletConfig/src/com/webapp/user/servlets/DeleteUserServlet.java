package com.webapp.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet(urlPatterns="/deleteUserServlet",initParams= {@WebInitParam(name="dbUrl",value="jdbc:mysql://localhost:3306/mydb"),
@WebInitParam(name="dbUser",value="root"),@WebInitParam(name="dbPassword",value="igate@123")})
public class DeleteUserServlet extends HttpServlet {
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
		String email=request.getParameter("email");
		int count;
		String sqlQuery="delete from user where email='"+email+"'";
		try {
			count=stmt.executeUpdate(sqlQuery);
			PrintWriter out=response.getWriter();
			if(count>0) {
				out.print("<html><body><h2>User deleted Successfully</h2></body></html>");
			}
			else {
				out.print("<html><body><h2>Error in deleting the user</h2></body></html>");
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
