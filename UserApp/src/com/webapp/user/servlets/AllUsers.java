package com.webapp.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllUsers
 */
@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sqlQuery="select * from user";
		try {
			ResultSet rs=stmt.executeQuery(sqlQuery);
			
			PrintWriter out=response.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.print("Firstname");
			out.print("</th>");
			out.print("<th>");
			out.print("Lastname");
			out.print("</th>");
			out.print("<th>");
			out.print("Email");
			out.print("</th>");
			out.print("</tr>");
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.print(rs.getString("firstname"));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString("lastname"));
				out.print("</td>");
				out.print("<td>");
				out.print(rs.getString("email"));
				out.print("</td>");
				out.print("</tr>");
			}
			
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
			
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
