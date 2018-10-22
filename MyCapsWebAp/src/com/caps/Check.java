package com.caps;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Check extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("name");
		String password=req.getParameter("pass");
		int count=1;
		long time=0;
		long diff=0;
		Date date=new Date();
		long time2=date.getTime();
		 diff=time2-time/1000;
		
		
PrintWriter out=resp.getWriter();
		
		if(count<=3) {
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
			{
				HttpSession session=req.getSession();
				
				session.setAttribute("name",id);
				session.setAttribute("time",count);
				if(session.isNew()==false) {
					
					
					++count;
					 time=session.getLastAccessedTime();
					
					if(count<=3) {
					
						
						
					session.setAttribute("time",count);
					out.print("Sorry, username or password error!");  
					 RequestDispatcher rd=req.getRequestDispatcher("login.html");
					 rd.include(req, resp);
					 
					
					}
					
				}
				else if(session.isNew()) {
					++count;
					session.setAttribute("time",count);

				out.print("Sorry, username or password error!");  
				 RequestDispatcher rd=req.getRequestDispatcher("login.html");
				 rd.include(req, resp);
				}
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
		else if(count>=3 && diff<3600) {
			
		out.println("account locked");
		}
		else if(count>=3 && diff>3600) {
			count=1;
		}

 }

		
		
	

}
