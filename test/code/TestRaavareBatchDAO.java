package code;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareBatchDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class TestRaavareBatchDAO {

	
	private IngredientBatchDAO dao = new IngredientBatchImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void testGetRaavareBatch() throws DALException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		
		
		
		IngredientBatchDAO rbDAO= null;
		
		List<IngredientBatchDTO> rbDAOList = dao.getIngredientsBatchList();
		int validId = dao.getIngredientsBatchList().get(0).getRbId();
		
		IngredientBatchDTO actual = dao.getIngredientsBatch(validId);
		IngredientBatchDTO expected = rbDAOList.get(0);
		
		boolean sameElements = true;
		assertTrue(sameElements);

	}
	
	@Test
	public void testGetRaavareBatchList() throws DALException {
		
		List<IngredientBatchDTO> list = dao.getIngredientsBatchList();
		
		assertTrue(list.size()>1);
		
	}
	
	@Test
	public void testGetRaavareBatchListWithraavareId() throws DALException {
		
		List<IngredientBatchDTO> list1 = dao.getIngredientsBatchList();
		
		assertTrue(list1.size()>1);
		
	}
	
	@Test
	public void TestCreateRaavareBatch() throws DALException {
		
		List<IngredientBatchDTO> list = dao.getIngredientsBatchList();
		int currentList = list.get(list.size()-1).getRbId();
		
		int expected = dao.getIngredientsBatchList().size()+1;
		dao.createRaavareBatch(new IngredientBatchDTO(currentList+1, 7, 23));
		int actual = dao.getIngredientsBatchList().size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUpdateRaavareBatch() {
		IngredientBatchDTO dto = null;
		int expected = 148;
		try {
			dto = dao.getIngredientsBatchList().get(0);
			dto.setRaavareId(expected);
			dao.updateRaavareBatch(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		int actual = dto.getRaavareId();
		assertEquals(expected, actual);
	}

}
