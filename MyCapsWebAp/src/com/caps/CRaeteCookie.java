package com.caps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cc")
public class CRaeteCookie extends HttpServlet{
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Cookie cookie=new Cookie("name", "tejesh");
	Cookie cookie1=new Cookie("mobile","6381733497");
	Cookie cookie2=new Cookie("address", "ongole");
	cookie.setMaxAge(60);
	cookie1.setMaxAge(60);
	cookie2.setMaxAge(60);
	resp.addCookie(cookie);
	resp.addCookie(cookie1);
	resp.addCookie(cookie2);
	PrintWriter out=resp.getWriter();
	out.print("cookie eaten");
	}
	
}
