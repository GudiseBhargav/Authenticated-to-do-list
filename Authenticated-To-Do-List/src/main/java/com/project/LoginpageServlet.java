package com.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginpageServlet")
public class LoginpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String duser = "root";
		String dpass = "2882561@Bc";
		String durl = "jdbc:mysql://localhost:3306/youtube?useSSL=false";
	    
		Connection con = null;
		PreparedStatement pst = null;
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		ResultSet res = null;
		
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(durl,duser,dpass);
			pst = con.prepareStatement("select* from users where uemail = ? and upwd = ?");
			
			pst.setString(1,username);
			pst.setString(2, password);
			
			res = pst.executeQuery();
			
			if(res.next()) {
				session.setAttribute("name", res.getString("uname"));
				rd = request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status", "failed");
				rd = request.getRequestDispatcher("login.jsp");
			}
			rd.forward(request, response);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("JDBC Driver not found.", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Database access error.", e);
		}finally {
			try {
				if (res != null) res.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
