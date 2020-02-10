package com.hhcpro.tools.api;




import com.hhcpro.tools.api.HBStatus.modes;

import junit.framework.TestCase;
//import junit.framework.Assert;

public class HBStatusTest extends TestCase {

	private static String name = "A.B.C.D";
	private HBStatus st;
	
	
	public HBStatusTest( ) {
		st = new HBStatus();
		st.init(name,modes.WRITE);
	}
	
	public void testIsUp() {
		
		try {
		   assertFalse(st.init(name,modes.WRITE));
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		st.up(); 
		assertTrue(st.IsUp());
		st.down();
		assertFalse(st.IsUp());
	}

	
}
