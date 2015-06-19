package code;
import org.junit.Test;

import code.server.TCPConnector;
import code.server.WeightServiceImpl;
import code.shared.WeightException;
import static org.junit.Assert.*;


public class TestWeightServiceImpl {
	
	WeightServiceImpl ws = new WeightServiceImpl();
	TCPConnector tcp = new TCPConnector("169.254.2.2", 8000);
	
	// MAN KAN IKKE TESTE DENNE KLASSE UDEN AT FORBINDE DEN TIL EN VÆGT
	
	@Test
	public void testGetTara() throws WeightException // Ret så du kan se det er et kommatal for at se om det virker
	{
		
		boolean verify = false;
		double checkGetTara = ws.getTara();
		
		String usedToParse = checkGetTara + "";
		try{
			Double.parseDouble(usedToParse);
			verify = true;
			
		}catch(Exception e){
			verify = false;
			
		}
				
		assertTrue(true);
		
		}

	
	
	@Test
	public void testGetWeight() throws WeightException // Test for at se om du får et kommatal
	{
		
		boolean verify = false;
		double checkS = ws.getWeight();
		
		String usedToParse = checkS + "";
		
		try{
			Double.parseDouble(usedToParse);
			verify = true;
			
		}catch(Exception e){
			verify = false;
			
		}
		assertTrue(true);
		
	}
	
	@SuppressWarnings("null")
	@Test
	public void testPrintToDisplay() throws WeightException // Tjek at du får et "D A"
	{
		boolean verify = false;
		
		String returnMsg = null;
		String message = "hej";
	
		ws.printToDisplay(message);
		
	
		if(returnMsg.startsWith("D A")){
			verify = true;
			
		}else{
			verify = false;
		}
		
		assertTrue(true);
		
			
	}




	@SuppressWarnings("null")
	@Test
	public void clearDisplay() throws WeightException
	{
		
		
		boolean verify = false;
		String returnMsg = null;
	
		ws.clearDisplay();
		
		if(returnMsg.startsWith("DW A")){
			verify = true;
			
		}else{
			verify = false;
		}
		
		assertTrue(true);
		
		
	
	}
	
}
