package com.rocky.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private static final String query= "SELECT BOOKNAME,AUTHORNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA WHERE ID=?";
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
		//Get the Id
		int id = Integer.parseInt(req.getParameter("id"));
		
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
					pst.setInt(1, id);
					ResultSet rs= pst.executeQuery();
					rs.next();
					pw.println("<h2 align='center' style='margin-bottom:5px'>Edit Your Book Details</h2>");
					pw.println("<form action='editurl?id="+id+"' method='post' style='margin-top:20px'>");
					pw.println("<table align='center' >");
					pw.println("<tr>");
					pw.println("<td>Book Name </td>");
					pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td>Author Name </td>");
					pw.println("<td><input type='text' name='authorName' value='"+rs.getString(2)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td>Book Edition </td>");
					pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(3)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<td>Book Price </td>");
					pw.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(4)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr style='margin-top:5px;'>");
					pw.println("<td><input type='submit' value='Edit' ></td>");
					pw.println("<td><input type='reset' value='Cancel'></td>");
					pw.println("</tr>");
					pw.println("</table>");
					pw.println("</form>");
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"/h1>");
		}
		pw.print("<div align='center'><a href='home.html'><input type='button'value='Home' style='margin-top:15px;background-color:rgb(220,53,69);color:white;border-color:grey;padding:5px;border-radius:5px'></a></div>");
	
		pw.println("<div align='center'><a href='bookList'><input type='button'value='BookList' style='background-color:grey;margin-top:5px;background-color:rgb(220,53,69);color:white;border-color:grey;padding:5px;border-radius:5px'></a></div>");
	     
	}

	
}
