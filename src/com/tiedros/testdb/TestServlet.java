package com.tiedros.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user="springstudent";
		String pass="springstudent";
		String jdbcURL="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			PrintWriter out=response.getWriter();
			out.println("Connecting to database: "+ jdbcURL);
			
			Class.forName(driver);
				Connection myCon= DriverManager.getConnection(jdbcURL, user, pass);
				System.out.println("Connection Successful!!!");
				myCon.close();
		}catch(Exception ex) {
			System.out.println("******Exception****** \n");
			ex.printStackTrace();
			throw new ServletException(ex);
		}
	}

}
