package com.webapp.testJBDC;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set up connection varaibles
		String url = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		String driver = "com.mysql.jdbc.Driver";
		
		//Get connection to DB
		try 
		{
			PrintWriter out = response.getWriter();
			out.println("Connecting to database: "+ url);
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			
			out.println("Connection Successful....");
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
