package code;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.IngredientDAO;
import code.database.IngredientDTO;
import code.database.IngredientImpl;
import code.database.*;

public class TestRaavareDAO {

	IngredientDAO rd = new IngredientImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {
		
		}
	}

	@Test
	public void testGetRaavare() throws DALException {
	

		IngredientDTO testVare = null;
		List<IngredientDTO> raavareList = rd.getIngredientList();
		int ID = raavareList.get(0).getIngredientId();
		testVare = rd.getIngredient(ID);

		IngredientDTO actual = testVare;
		IngredientDTO expected = raavareList.get(0);

		boolean elementsAreTheSame = true;
		
		if (actual.getIngredientId() 	!= expected.getIngredientId()) 	   		elementsAreTheSame = false;
		if (!actual.getIngredientName().equals(expected.getIngredientName())) 	elementsAreTheSame = false;
		if (!actual.getLeverandoer().equals(expected.getLeverandoer())) 		elementsAreTheSame = false;

		assertTrue(elementsAreTheSame);

	}

	@Test
	public void testGetRaavareList() throws DALException{

		List<IngredientDTO> list = rd.getIngredientList();
		
		assertTrue(list.size()>1);
	} 
	
	@Test
	public void TestCreateRaavare() throws DALException{
	
		List<IngredientDTO> list = rd.getIngredientList();
		int currentHighestID  = list.get(list.size()-1).getIngredientId();
		
		int expected = rd.getIngredientList().size()+1;
		rd.createIngredient(new IngredientDTO(currentHighestID+1, "Banan", "stedet"));
		int actual =  rd.getIngredientList().size();
		
		

		assertEquals(expected, actual);

	}
	
	@Test
	public void testUpdateRaaVare(){
		IngredientDTO dto = null;
		String expected = "bullerbassen";
		try {
			dto = rd.getIngredientList().get(0);
			dto.setLeverandoer(expected);
			rd.updateIngredient(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		String actual = dto.getLeverandoer();
		assertEquals(expected, actual);
		
	}
}
