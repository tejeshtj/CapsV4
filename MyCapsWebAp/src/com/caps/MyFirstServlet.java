package com.caps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/date")
public class MyFirstServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String fname = req.getParameter("fname");
		//String lname = req.getParameter("lname");
		
		//int i = 10/0;
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>The Current Time is: "+ new Date() +"</h1>");
		//out.println("<h1>Name: "+fname+" "+lname+"</h1>");
		//ServletContext ctx=getServletContext();
		//String email=ctx.getInitParameter("email");
		//out.print(email);
		ServletConfig con=getServletConfig();
		String mail=con.getInitParameter("email");
		out.print(mail);
		
		resp.setContentType("text/html");
	}
}
