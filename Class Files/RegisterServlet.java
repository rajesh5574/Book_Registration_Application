package com.rocky.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final String query= "Insert into BOOKDATA (BOOKNAME,AUTHORNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?,?)";
       private static final String url ="jdbc:mysql://localhost:3310/book";
       private static final String user="root";
       private static final String pass="raj_137_rocky";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	// get PrintWriter
		PrintWriter pw = res.getWriter(); 
		res.setContentType("text/html");
		
		//GET the BookInfo
		String bookName=req.getParameter("bookName");
		String authorName = req.getParameter("authorName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice =Float.parseFloat( req.getParameter("bookPrice"));
		
		
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
				pst.setString(1,bookName);
				pst.setString(2, authorName);
				pst.setString(3, bookEdition);
				pst.setFloat(4, bookPrice);
				int count = pst.executeUpdate();
				if(count==1) pw.println("<h2 align='center' style='margin-top:50px;'> Record is Registered Successfully</h2>");
				else pw.println("<h2 align='center' style='margin-top:50px;'> Record is Not Registered ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"/h1>");
		}
		pw.println("<div align='center'><a href='home.html'><input type='button'value='Home' style='margin-top:15px;background-color:rgb(13,110,253);color:white;'></a></div>");
		pw.println("<div align='center'><a href='bookList'><input type='button'value='BookList' style='margin-top:5px;background-color:rgb(13,110,253);color:white;'></a></div>");
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req,res);
	}

}
