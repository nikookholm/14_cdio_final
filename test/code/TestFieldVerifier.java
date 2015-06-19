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
	
	@Test
	public void testPswWith3Char(){
		
		boolean actual = FieldVerifier.isPswdValid("3ef");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testPswWith10Char(){
		
		boolean actual = FieldVerifier.isPswdValid("3ef1lkfnff");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testPswWith7Char(){
		
		boolean actual = FieldVerifier.isPswdValid("3efhaok");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testLeverandoerWith1Char(){
		
		boolean actual = FieldVerifier.leverandoerValid("f");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testLeverandoerWith10Char(){
		
		boolean actual = FieldVerifier.leverandoerValid("3ef1lkfnff");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testLeveandoerWith22Char(){
		
		boolean actual = FieldVerifier.leverandoerValid("3efhakdnamodldbsif  ok");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIngNameWith1Char(){
		
		boolean actual = FieldVerifier.ingredientName("f");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIngNameWith10Char(){
		
		boolean actual = FieldVerifier.ingredientName("3ef1lkfnff");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testIngNameWith22Char(){
		
		boolean actual = FieldVerifier.ingredientName("3efhakdnamodldbsif  ok");
		
		assertFalse(actual);
		
	}
	@Test
	public void testIngIdWith10int(){
		
		boolean actual = FieldVerifier.ingredientId("9876543210");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testIngId(){
		
		boolean actual = FieldVerifier.ingredientId("78900");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testIngIdWithSpace(){
		
		boolean actual = FieldVerifier.ingredientId(" ");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testPbNoWith10int(){
		
		boolean actual = FieldVerifier.ispbNoValid("9876543210");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testPbNo(){
		
		boolean actual = FieldVerifier.ispbNoValid("78900");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testPbNoWithSpace(){
		
		boolean actual = FieldVerifier.ispbNoValid(" ");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testRbNoWith10int(){
		
		boolean actual = FieldVerifier.isrbNoValid("9876543210");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testRbNo(){
		
		boolean actual = FieldVerifier.isrbNoValid("78900");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testRbNoWithSpace(){
		
		boolean actual = FieldVerifier.isrbNoValid(" ");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testReceptNoWith10int(){
		
		boolean actual = FieldVerifier.isReceptNoValid("9876543210");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testReceptNo(){
		
		boolean actual = FieldVerifier.isReceptNoValid("78900");
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testReceeptNoWithSpace(){
		
		boolean actual = FieldVerifier.isReceptNoValid(" ");
		
		assertFalse(actual);
		
	}
	@Test
	public void testStatusValidWith4(){
		
		boolean actual = FieldVerifier.isStatusValid("4");
		
		assertFalse(actual);
	}
	
	@Test
	public void testStatusValidWith4ints(){
		
		boolean actual = FieldVerifier.isStatusValid("1854");
		
		assertFalse(actual);
	}
	
	@Test
	public void testStatusValidWithString(){
		
		boolean actual = FieldVerifier.isStatusValid("sldf");
		
		assertFalse(actual);
	}
	
	@Test
	public void testLettersOnlyVsInt(){
		
		boolean actual = FieldVerifier.isLettersOnly("132356643");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testLettersOnlyVsSimb(){
		
		boolean actual = FieldVerifier.isLettersOnly("#€%&/(");
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testLettersOnlyVsLetters(){
		
		boolean actual = FieldVerifier.isLettersOnly("fglsdjfgjsdj");
		
		assertTrue(actual);
		
	}
	
}


























