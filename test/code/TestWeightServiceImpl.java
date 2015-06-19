package code;
import org.junit.Test;

import code.server.TCPConnector;
import code.server.WeightServiceImpl;
import code.shared.WeightException;
import static org.junit.Assert.*;


public class TestWeightServiceImpl {
	
	WeightServiceImpl ws = new WeightServiceImpl();
	TCPConnector tcp = new TCPConnector("169.254.2.2", 8000);
	
	//TJEK PÅ ALLE RETUR VÆRDIER.. SÅ VI HAR NOGET AT TESTE OP I MOD.
	
	@Test
	public void testGetTara() throws WeightException // Ret så du kan se det er et kommatal for at se om det virker
	{

		double tool = 0.000;
		double checkGetTara =  ws.getTara();
		
		System.out.println("her er " + ws.getTara());
		
		assertTrue(checkGetTara==tool);
		
		}

	
	
	@Test
	public void testGetWeight() throws WeightException // Test for at se om du får et kommatal
	{
		
		double displayNetto = 0.000;
		//double checkNetto;      Hvis vi gerne vil tjekke om der er en netto vægt kan vi tjekke checkS > checknetto
		double checkS = ws.getWeight();
		
		assertTrue(checkS==displayNetto);
	
		
	}
	
	@Test
	public void testPrintToDisplay() throws WeightException // Tjek at du får et "D A"
	{
		String test = null;
		String message = "hej";
		ws.printToDisplay(message);
		
		String expected = message;
		String actual = test;
		
		
		assertArrayEquals(expected, actual);
	
		
		
		
	}

	private void assertArrayEquals(String expected, String actual) {  // Findes allerede i jave ?
		
	}



	@Test
	public void clearDisplay() throws WeightException
	{
		
		// Nice! :D
		ws.clearDisplay();
		
		
	
	}
	
}
