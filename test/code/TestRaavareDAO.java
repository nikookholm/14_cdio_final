package code;


import static org.junit.Assert.*;
import daoimpl01917.MySQLRaavareDAO;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import dto01917.RaavareDTO;

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
		List<IngredientDTO> raavareList = rd.getRaavareList();
		int ID = raavareList.get(0).getRaavareId();
		testVare = rd.getRaavare(ID);

		IngredientDTO actual = testVare;
		IngredientDTO expected = raavareList.get(0);

		boolean elementsAreTheSame = true;
		
		if (actual.getRaavareId() 	!= expected.getRaavareId()) 	   elementsAreTheSame = false;
		if (!actual.getRaavareNavn().equals(expected.getRaavareNavn())) elementsAreTheSame = false;
		if (!actual.getLeverandoer().equals(expected.getLeverandoer())) elementsAreTheSame = false;

		assertTrue(elementsAreTheSame);

	}

	@Test
	public void testGetRaavareList() throws DALException{

		List<IngredientDTO> list = rd.getRaavareList();
		
		assertTrue(list.size()>1);
	} 
	
	@Test
	public void TestCreateRaavare() throws DALException{
	
		List<IngredientDTO> list = rd.getRaavareList();
		int currentHighestID  = list.get(list.size()-1).getRaavareId();
		
		int expected = rd.getRaavareList().size()+1;
		rd.createRaavare(new IngredientDTO(currentHighestID+1, "Banan", "stedet"));
		int actual =  rd.getRaavareList().size();
		
		

		assertEquals(expected, actual);

	}
	
	@Test
	public void testUpdateRaaVare(){
		IngredientDTO dto = null;
		String expected = "bullerbassen";
		try {
			dto = rd.getRaavareList().get(0);
			dto.setLeverandoer(expected);
			rd.updateRaavare(dto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = dto.getLeverandoer();
		assertEquals(expected, actual);
		
	}
}
