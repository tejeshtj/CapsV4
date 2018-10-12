package com.caps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteCookie extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cook=req.getCookies();
		if(cook!=null) {
			for(Cookie c:cook) {
				c.setMaxAge(0);
				resp.addCookie(c);
				PrintWriter out=resp.getWriter();
				out.print("done dude....");
			}
		}
	}

}
