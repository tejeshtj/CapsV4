package com.caps;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/contact")
public class Contact extends HttpServlet {

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			FileReader fr=new FileReader("E:/mail.properties");
			Properties prop=new Properties();
			prop.load(fr);
			
			
			
		  final String user=prop.getProperty("user");;
		  final String password=prop.getProperty("password");;
		  String name=req.getParameter("Name");
		  String mail=req.getParameter("Email");
		  String info=req.getParameter("Message");
		  //String to="suchismita435@gmail.com";
		 Scanner sc=new Scanner(System.in);
		  System.out.println("enter the to address:");
		   String to=sc.nextLine();
		  sc.close();
		   //String cc="";
		   //Get the session object
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getDefaultInstance(props,
		    new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,password);
		      }
		    });
		   	
		   //Compose the message
		    try {
		     MimeMessage message = new MimeMessage(session);
		     message.setFrom(new InternetAddress(user));
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		    // message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
		     message.setSubject("name= "+name+"  email="+mail);
		     message.setText("information:"+info);
		     
		    //send the message
		     Transport.send(message);

		     System.out.println("message sent successfully...");
		     PrintWriter out = resp.getWriter();
		     out.println("dear "+name+" your query has been submitted");
		 
		     } catch (MessagingException e) {e.printStackTrace();}

}
}
