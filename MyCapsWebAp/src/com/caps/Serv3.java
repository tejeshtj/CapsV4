package com.caps;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/serv3", 
		initParams=@WebInitParam(name="pwd",value="root"))
public class Serv3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Dog d = new Dog();
		d.setName("Bingo");
		d.setColor("White");
		d.setBreed("Pomerian");

		req.setAttribute("dog", d);
		
		Cat c=new Cat();
		c.setName("lucy");
		c.setGender("female");
		c.setAge("ten months");
		
		ServletContext ctx=getServletContext();
		ctx.setAttribute("cat", c);
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/serv4");
		dispatcher.forward(req, resp);
	}
}