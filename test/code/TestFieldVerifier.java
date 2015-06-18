package code;


import org.junit.Test;
import static org.junit.Assert.*;

import code.shared.FieldVerifier;

public class TestFieldVerifier {
	
	@Test
	public void testIsValidName()
	{
		boolean actual = FieldVerifier.isValidName(" ");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIsValidNameLength()
	{
		boolean actual = FieldVerifier.isValidName("qwertyuiopasdfghjklzx");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIsValidName2Short()
	{
		boolean actual = FieldVerifier.isValidName("x");
		
		assertFalse(actual);
		
	}

}
