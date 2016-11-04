package Concatenate;

import static org.junit.Assert.*;

import org.junit.Test;


public class JUnit {
	private ConcatenateStrings cs = new ConcatenateStrings();
	
	@Test
	public void testConcatenate() {	
		int result = cs.multiply(2, 2);
		assertEquals(4, result);
	}

}
