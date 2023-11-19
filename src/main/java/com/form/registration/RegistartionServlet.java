package com.form.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.form.DBConnection.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistartionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re_password");
		
		String query = "insert into users(username,email,password) values(?,?,?)";
		Connection con = DBConnection.getConnection().getCon();
		PreparedStatement stmt = null;
		RequestDispatcher dispatcher = null;
		
		try {
			stmt =con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, email);
			stmt.setString(3, password);
			
			int rowCount = stmt.executeUpdate();
			
			if(rowCount > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("registration.jsp");
				dispatcher.forward(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
