package com.caps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/rc")
public class ReadCoookie extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		Cookie[] cook=req.getCookies();
		
		if(cook!=null)
		{
			
			for(Cookie c:cook)
			{
			out.print("<h1>"+c.getName()+"---"+c.getValue()+"</h1>");
			
			out.println(c.getMaxAge());
			}
		}
		else
		{
			out.print("<h1>cookies not found feed me</h1>");
		}
	}

}
