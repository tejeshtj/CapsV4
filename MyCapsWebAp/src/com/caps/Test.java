package com.caps;

import java.time.Duration;
import java.time.Instant;

public class Test {
	public static void main(String[] args) throws Exception
	
	{
		Instant start = Instant.now();
		
		Thread.sleep(3000);
		
		Instant stop = Instant.now();
		 
		Duration timeDiff = Duration.between(start, stop);
		
		System.out.println(timeDiff.toMinutes());
		
	}
}
