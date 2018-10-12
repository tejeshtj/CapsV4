package com.caps;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String id=req.getParameter("email");
		String password=req.getParameter("pass");
		
		PrintWriter out=resp.getWriter();
		
		java.sql.Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			java.sql.Driver ref=new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(ref);
			
			String dbUrl="jdbc:mysql://localhost:3306/capsv4_db";
			String filePath = "E:/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(dbUrl, prop);
			
			String sql="select * from users where email=? and password=?;";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				 HttpSession session=req.getSession(true);  
			        session.setAttribute("user",id); 
			        req.getRequestDispatcher("mail.html").include(req, resp);
			        
			        String mail=rs.getString("email");
			        System.out.println(mail);
			}
			else
			{
				out.print("Sorry, username or password error!");  
				 RequestDispatcher rd=req.getRequestDispatcher("login.html");
				 rd.forward(req, resp);
			}
				
						}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally
		{
			
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	 
		
	}

}
