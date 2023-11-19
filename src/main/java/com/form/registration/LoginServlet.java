package com.form.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.form.DBConnection.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		
		String query = "select * from users where email=? and password=?";
		Connection con = DBConnection.getConnection().getCon();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		
		
		try {
			stmt = con.prepareStatement(query);
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			
			
			if(rs.next()) {
				session.setAttribute("username",rs.getString("username"));
				dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
