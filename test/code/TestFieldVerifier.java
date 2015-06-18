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
	
	@Test
	public void testIsCPRwithSpace(){
		
		boolean actual = FieldVerifier.isCPRValid(" ");
		
		assertFalse(actual);
	}
	
	@Test
	public void testIsCPRwithLetter(){
		
		boolean actual = FieldVerifier.isCPRValid("x");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIsCPRValid10(){
		
		boolean actual = FieldVerifier.isCPRValid("1234567890");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testIsCPRValid2(){
		
		boolean actual = FieldVerifier.isCPRValid("22");
		
		assertFalse(actual);
	}
	
	@Test
	public void testInitials(){
		
		boolean actual = FieldVerifier.isInitialsValid("333");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testInitialsWith1Char(){
		
		boolean actual = FieldVerifier.isInitialsValid("3");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testInitialsWith19Char(){
		
		boolean actual = FieldVerifier.isInitialsValid("3asfasf45737y334%&&");
		
		assertFalse(actual);
		
	}
	
	

}


























