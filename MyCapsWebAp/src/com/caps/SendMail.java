package com.caps;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/mail")
public class SendMail extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		 final String user=req.getParameter("from");
     	
			final String password=req.getParameter("password");
			 String to=req.getParameter("to");
			 String subject=req.getParameter("subject");
			 String message1=req.getParameter("message");
			 
		PrintWriter out=resp.getWriter();
		HttpSession session=req.getSession(false);
	    String name=(String)session.getAttribute("person");
	    System.out.println(name);
        if(session!=null) {
		
       
        	 
		 System.out.println(user);
		 System.out.println(password);
		 
		 Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
		   
		   Session session1 = Session.getDefaultInstance(props,
		    new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,password);
		      }
		    });

		   //Compose the message
		    try {
		     MimeMessage message = new MimeMessage(session1);
		     message.setFrom(new InternetAddress(user));
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		    // message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
		     message.setSubject(subject);
		     message.setText(message1);
		     
		    //send the message
		     Transport.send(message);

		     System.out.println("message sent successfully...");
		    
		     out.print("mail sent");
		 
		     } catch (MessagingException e) {
		    	 e.printStackTrace();
		    	 out.print("<h1>mail not sent due to technical issues...! sorry for incovenience</h1>");
		     }
		 }
        else{  
            out.print("Sorry, username or password error!");  
            resp.sendRedirect("index.html");   
        }  
	}

}
