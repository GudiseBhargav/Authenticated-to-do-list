package com.project.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String duser = "root";
		String dpass = "2882561@Bc";
		String durl = "jdbc:mysql://localhost:3306/youtube?useSSL=false";
	    
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(durl, duser, dpass);
			
			pst = con.prepareStatement("INSERT INTO users(uname, upwd, uemail, umobile) values(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2,upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);

			
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			
			int rowcount = pst.executeUpdate();
			if(rowcount > 0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	}
