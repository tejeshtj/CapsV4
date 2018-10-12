package com.caps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/msg")
public class Msg extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg1=req.getParameter("msg");
		
		System.out.println(msg1);
		PrintWriter out=resp.getWriter();
		
			System.out.println("enter your message");
			Scanner sc= new Scanner("System.in");
			String mymsg=sc.nextLine();
			out.println(mymsg);
			sc.close();
		
	}
}
