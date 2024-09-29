package com.rocky.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private static final String query= "DELETE FROM BOOKDATA WHERE ID=?;";
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
					int count= pst.executeUpdate();
					if (count==1) pw.println("<h2> Record is Deleted Succesfully!!!<h2>");
					else pw.println("<h2> Record Couldnot Deleted <h2>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"/h1>");
		}
		pw.println("<div align='center'><a href='home.html'><input type='button'value='Home' style='margin-top:20px;background-color:grey;'></a></div>");
		pw.println("<div align='center'><a href='bookList'><input type='button'value='BookList' style='margin-top:20px;background-color:grey;'></a></div>");
	     
	}

}
