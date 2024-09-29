package com.rocky.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final String query= "SELECT ID,BOOKNAME,AUTHORNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";
       private static final String url ="jdbc:mysql://localhost:3310/book";
       private static final String user="root";
       private static final String pass="raj_137_rocky";
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	// get PrintWriter
		PrintWriter pw = res.getWriter(); 
		res.setContentType("text/html");
			
		//JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get The Connection
		
		try(Connection con= DriverManager.getConnection(url,user,pass);
				PreparedStatement pst = con.prepareStatement(query);)
		{
					ResultSet rs= pst.executeQuery();	
					pw.println("<table border='1' align='center' style='border-collapse:collapse;margin-top:20px;width:50%;border-radius:5px;'>");
					pw.println("<tr style='background-color:orange;padding:10px;border:1.5px solid black; color:white;'>");
					pw.println("<th style='padding:10px;' > Book Id </th>");
					pw.println("<th> Book Name </th>");
					pw.println("<th> Author Name </th>");
					pw.println("<th> Book Edition </th>");
					pw.println("<th> Book Price </th>");
					pw.println("<th> Edit </th>");
					pw.println("<th> Delete </th>");
					pw.println("</tr>");
					while(rs.next()) {
						pw.println("<tr align='center' style='padding:2px;'>");
						pw.println("<td>"+rs.getInt(1)+"</td>");
						pw.println("<td>"+rs.getString(2)+"</td>");
						pw.println("<td>"+rs.getString(3)+"</td>");
						pw.println("<td>"+rs.getString(4)+"</td>");
						pw.println("<td>"+rs.getFloat(5)+"</td>");
						pw.println("<td><a  href='editScreen?id="+rs.getInt(1)+"'><input type='submit' style='color:white;margin-top:4px; margin-bottom:4px;background-color:rgb(13,110,253);' value='Edit'></a></td>");
						pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'><input type='submit' style='color:white;margin-top:4px; margin-bottom:4px;background-color:rgb(13,202,240);' value='Delete'></a></td>");
						pw.println("</tr>");
					}
					pw.println("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"/h1>");
		}
		pw.println("<div align='center'><a href='home.html'><input type='submit'value='Home' style='margin-top:20px;color:white;background-color:rgb(220,53,69) '></a></div>");
	}

	

}
