package com.caps;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

@WebServlet("/signup")
public class SignUp extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name=req.getParameter("username");
		String mail=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String password=req.getParameter("pass");
		
		java.sql.Connection con=null;
		PreparedStatement pstmt=null;
		PrintWriter out=resp.getWriter();		
		try
		{
			java.sql.Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			
			String dbUrl="jdbc:mysql://localhost:3306/capsv4_db";
			String filePath = "E:/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(dbUrl, prop);
			
			String sql="insert into users values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mail);
			pstmt.setString(2,name);
			pstmt.setString(3,mobile);
			pstmt.setString(4,password);
			
			int count=pstmt.executeUpdate();
			
			if(count>0) {
				out.print("<h1>signup succesful</h1>");
			}
			else
			{
				out.print("email or mobile exists");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally 
		{
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
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
