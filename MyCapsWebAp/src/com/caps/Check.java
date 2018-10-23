package com.caps;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/check")
public class Check extends HttpServlet {
	int count=0;
	Instant start;
	Instant stop = null;
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("name");
		String password=req.getParameter("pass");
		
		System.out.println(count);
		
		
		
PrintWriter out=resp.getWriter();
		
		if(count<3) {
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
				 session.setMaxInactiveInterval(-1);
			        session.setAttribute("user",id); 
			        req.getRequestDispatcher("mail.html").include(req, resp);
			        
			        String mail=rs.getString("email");
			        System.out.println(mail);
			}
			else
			{/*
				HttpSession session=req.getSession();
				
				session.setAttribute("name",id);*/
				
				
					
					++count;
					start = Instant.now();
					System.out.println(start);
											
					//session.setAttribute("time",count);
					System.out.println(count);
					out.print("Sorry, username or password error!");  
					
				
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
					
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
 
		
	}
		}
		//else if(count>3 && diff<50) {
		else {
			stop=Instant.now();
			long timeDiff = Duration.between(start, stop).toMillis();
			System.out.println(timeDiff);
			if(timeDiff<10000) {
		out.println("account locked");
		System.out.println("lock");
			}else {
				count=0;
			}
			}
		/*else if(count>3 && diff>5) {
			count=1;
		}*/

 }

		
		
	

}
