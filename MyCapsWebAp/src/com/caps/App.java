package com.caps;

import java.io.*;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class App {

	final static Logger logger=Logger.getLogger(App.class);
	public static void main(String[] args) {
		logger.info("logging started..");
		Layout layout=new SimpleLayout();
		Appender appender;
		try {
			appender=new FileAppender(layout,"E:/log.txt",true);
			logger.addAppender(appender);
			logger.info("giving file path");
			String path="E:/log.txt";
			logger.info("loading file reader..");
			FileReader fr=new FileReader(path);
			logger.info("creating buff reader and passing file reader object...");
			BufferedReader br=new BufferedReader(fr);
			logger.info("file is getting readed");
			
			String line=null;
			
			while((line=br.readLine())!=null){	
				System.out.println(line);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.fatal("this is error message");
			System.out.println("success....");
		}
		logger.info("loging ended");
	}
}
