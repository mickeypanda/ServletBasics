package com.webapp.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = { "/updateUserServlet" })
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;   
    private Statement stmt;

	private String dbURL="jdbc:mysql://localhost:3306/mydb";
	private String user="root";
	private String password="igate@123";
	
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,user,password);
			stmt=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		int count;
		String sqlQuery="update user set password='"+password+"' where email='"+email+"'";
		try {
			count=stmt.executeUpdate(sqlQuery);
			PrintWriter out=response.getWriter();
			if(count>0) {
				out.print("<html><body><h2>User updated Successfully</h2></body></html>");
			}
			else {
				out.print("<html><body><h2>Error updating the user</h2></body></html>");
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
