package com.caps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/receive")
public class Receive extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  
		 String email_id= req.getParameter("mail");
		  String password=req.getParameter("pass");
		
		req.getSession(true);
		
		
		  PrintWriter out=resp.getWriter();
		  
		  Properties properties = new Properties();
		  //You can use imap or imaps , *s -Secured
		  properties.put("mail.store.protocol", "imaps");
		  //Host Address of Your Mail
		  properties.put("mail.imaps.host", "imap.gmail.com");
		  //Port number of your Mail Host
		  properties.put("mail.imaps.port", "993");
		 
		  //properties.put("mail.imaps.timeout", "10000");
		 
		  try {
		 
		   //create a session  
		   Session session1 = Session.getDefaultInstance(properties, null);
		   //SET the store for IMAPS
		   Store store = session1.getStore("imaps");
		 
		   System.out.println("Connection initiated......");
		   //Trying to connect IMAP server
		   store.connect(email_id, password);
		   System.out.println("Connection is ready :)");
		 
		 
		   //Get inbox folder 
		   Folder inbox = store.getFolder("inbox");
		   //SET readonly format (*You can set read and write)
		   inbox.open(Folder.READ_ONLY);
		 
		 
		   //Display email Details 
		 
		   //Inbox email count
		   int messageCount = inbox.getMessageCount();
		//   System.out.println("Total Messages in INBOX:- " + messageCount);
		
		   out.print("<head>\r\n" + 
		   		"  <meta charset=\"UTF-8\"><title>Messages Inbox</title><style> * {margin: 0px; padding: 0px; border: none;box-sizing: border-box;}html, body {background: #333B3D;font: 14px Tahoma, Geneva, sans-serif;width: 100%; height: 100%;}\r\n" + 
		   		"header {margin: 0px auto;position: relative;width: 90%; max-width: 960px;height: 100px;}header h1 {margin: 0px; padding: 50px 0px 0px;color: #EEE;font-size: 30px;}.mailbox {margin: 0px auto -100px; position: relative;\r\n" + 
		   		"  background: #CCDDED;border-radius: 0px 5px 0px 0px;width: 90%; max-width: 960px;}.nav { position: absolute; top: 0px; bottom: 0px;background: #EEE;width: 200px;}\r\n" + 
		   		".nav a {display: block;padding: 5px 10px;color: #3D9FC4;font-weight: bold;text-decoration: none;}.nav a.active {background: #CCDDED;}.nav a:first-child {margin: 10px 0px 10px;}.messages {padding: 10px 10px 10px 210px; width: 100%;}\r\n" + 
		   		".actions-dropdown {display: block;float: right;position: relative;margin: 5px 10px 10px 0px;}.actions-dropdown label { padding: 5px 10px; background: #EEE; color: #666;border-radius: 5px;cursor: pointer;}\r\n" + 
		   		".actions-dropdown label span {color: #999;font-size: 10px;}.actions-dropdown:hover ul { display: block;}.actions-dropdown ul {display: none;position: absolute;list-style: none;background: #EEE;border-radius: 5px;width: 100%;}\r\n" + 
		   		".actions-dropdown li {padding: 5px 10px;color: #666;cursor: pointer;}.actions-dropdown li:hover {color: #000;}.actions-dropdown li:first-child { margin-top: 5px;}input[name=search] {display: block;\r\n" + 
		   		"float: right;margin-bottom: 10px;padding: 5px 10px;background: #EEE;font: 14px Tahoma, Geneva, sans-serif; border-radius: 5px;}input[name=search]:focus { outline: none;}.message {clear: both;\r\n" + 
		   		" margin-bottom: 5px;position: relative;padding: 5px;background: #bbd0e4;border-radius: 5px;}.message input {position: absolute;top: 8px; left: 10px;width: 30px;}.message .sender {display: block;position: absolute;\r\n" + 
		   		"left: 40px;color: #000;font-weight: bold;width: 90px;}.message .title { display: inline-block; padding-left: 150px; width: 100%;}.message .date {position: absolute; right: 10px;}\r\n" + 
		   		" </style></head><body><header><h1>messages</h1></header><div class=\"mailbox\"><div class=\"nav\"> <a href=\"mail.html\">compose</a> <a href=\"#\" class=\"active\">inbox</a></div>");
		   for (int i = messageCount; i>0; i--) {
			    //System.out.println("Mail Subject:- " + inbox.getMessage(messageCount - i).getSubject());
			    //System.out.println("Mail From:- " + inbox.getMessage(messageCount - i).getFrom()[0]);
			    //System.out.println("Mail Content:- " + inbox.getMessage(messageCount - i).getContent().toString());
			    //req.setAttribute("date1",inbox.getMessage(messageCount-10).getSentDate().toString());
			   Address from=inbox.getMessage(i).getFrom()[0];
			  
		      

			   String content=inbox.getMessage(i).getContent().toString();
		       
			   String subject=inbox.getMessage(i).getSubject();
			   String date=inbox.getMessage(i).getSentDate().toString();
			   out.print("<br/><br/><br/> <div class=\"message\"><input type=\"checkbox\" /><span class=\"sender\">"+from+"</span><span class=\"date\">"+date+"</span><span class=\"title\">"+content+"</span>");
		   }
		   inbox.close(true);
		  store.close();
		  out.print("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script></body></html>");
		 
		  } catch (Exception e) {
		   e.printStackTrace();
		   out.print("<h1>mail or password in correct"
		   		+ "or due to technical issues</h1>");
		  }
		 
	}

}
