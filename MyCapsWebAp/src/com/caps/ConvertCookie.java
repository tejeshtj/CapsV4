package com.caps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/convert")
public class ConvertCookie extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Cookie[] cook=req.getCookies();
			PrintWriter out=resp.getWriter();
			if(cook!=null) {
				for(Cookie c:cook) {
					if(c.getName().equals("name")) {
						c.setMaxAge(-1);
						out.println(c.getName());
						resp.addCookie(c);
						out.println("i have taken the cookie");
					}
					else {
						c.setMaxAge(3600);
						out.println(c.getName());
						resp.addCookie(c);
						out.println("cookie life increased");
					}
					
				}
			}
			else {
				out.println("i havent get any cookies");
			}
		}
}
