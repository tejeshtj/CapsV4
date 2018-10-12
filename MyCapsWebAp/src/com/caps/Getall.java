package com.caps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;
@SuppressWarnings("serial")
@WebServlet("/get")
public class Getall extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reg=req.getParameter("number");
		Connection con = null;
		PreparedStatement pstmt = null;
		//Statement stmt=null;
		ResultSet rs = null;
		PrintWriter out=resp.getWriter();
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
						String dbUrl="jdbc:mysql://localhost:3306/capsv4_db"
								+ "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from students_info where sid=?";

			int sid=Integer.parseInt(reg);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,sid);
			rs = pstmt.executeQuery();
			
			out.print("<html>");
			out.print("<body>");
			out.print("<table>");
			out.print("<tr><td>sid</td><td>firstname</td><td>lastname</td><td>gender</td><td>type</td><td>password</td></tr>");
			/*
			 * 4. Process the results
			 */

			while
			(rs.next()){
				int regno = rs.getInt("sid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				String type=rs.getString("type"); 
				String passwd = rs.getString("password");

				out.print("<tr><td>"+regno+"</td>");
				out.print("<td>"+firstname+"</td>");
				out.print("<td>"+lastname+"</td>");
				out.print("<td>"+gender+"</td>");
				out.print("<td>"+type+"</td>");
				out.print("<td>"+passwd+"</td></tr>");
				/*System.out.println(regno);
					System.out.println(firstname);
					System.out.println(lastname);
					System.out.println(gender);
					System.out.println(type);
					System.out.println(passwd);*/
			}
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			/*
			 * 5. Close all the JDBC Objects
			 */

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
