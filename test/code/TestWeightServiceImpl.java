package code;
import org.junit.Test;

import code.server.WeightServiceImpl;
import code.shared.WeightException;


public class TestWeightServiceImpl {
	
	WeightServiceImpl ws = new WeightServiceImpl();
	
	//TJEK PÅ ALLE RETUR VÆRDIER.. SÅ VI HAR NOGET AT TESTE OP I MOD.
	
	@Test
	public void testGetTara() throws WeightException
	{
		ws.getTara();
		
		
		
	}
	
	
	@Test
	public void testGetWeight() throws WeightException
	{
		ws.getWeight();
		
	}
	
	@Test
	public void testPrintToDisplay() throws WeightException
	{
		
		String message = "hej";
		ws.printToDisplay(message);
		
		
	}
	
	@Test
	public void clearDisplay() throws WeightException
	{
		ws.clearDisplay();
	
	}
	
}
