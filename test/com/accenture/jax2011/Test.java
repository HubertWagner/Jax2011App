package com.accenture.jax2011;


import junit.framework.TestCase;

import org.junit.Before;

import com.accenture.jax2011.server.GreetingServiceImpl;

public class Test extends TestCase {

	@Before
	public void setUp() throws Exception {
	}
	
	public void testGreeting(){
		GreetingServiceImpl service = new GreetingServiceImpl();
		service.writeToDB("a","b");
	}

}
