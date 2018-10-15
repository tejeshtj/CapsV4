package com.caps;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/serv1", 
		initParams=@WebInitParam(name="pwd",value="root"))
public class Serv1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Dog d = new Dog();
		d.setName("Bingo");
		d.setColor("White");
		d.setBreed("Pomerian");

		req.setAttribute("dog", d);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/serv2");
		dispatcher.forward(req, resp);
	}
}