package com.caps;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            HttpSession session=request.getSession(false); 
           if(session!=null) {   
            request.getRequestDispatcher("login.html").include(request, response);  
             Cookie[] cook=request.getCookies();
             for(Cookie c:cook) {
            	 if(c.getName().equals("JSESSIONID")) {
            		 c.setMaxAge(0);
            		
            		 response.addCookie(c);
            	 }
             }
            
            session.invalidate();  
              
            out.print("You are successfully logged out!");  
           }
           else {
        	   request.getRequestDispatcher("login.html").include(request, response);
        	   out.print("<h1>login first</h1>");
           }
            out.close();  
    }  
}  