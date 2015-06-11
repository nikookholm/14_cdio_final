package code;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.IngredientBatchDAO;
import code.database.IngredientBatchDTO;
import code.database.IngredientBatchImpl;

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
		
		List<IngredientBatchDTO> rbDAOList = dao.getIngredientBatchList();
		int validId = dao.getIngredientBatchList().get(0).getRbId();
		
		IngredientBatchDTO actual = dao.getIngredientBatch(validId);
		IngredientBatchDTO expected = rbDAOList.get(0);
		
		boolean sameElements = true;
		assertTrue(sameElements);

	}
	
	@Test
	public void testGetRaavareBatchList() throws DALException {
		
		List<IngredientBatchDTO> list = dao.getIngredientBatchList();
		
		assertTrue(list.size()>1);
		
	}
	
	@Test
	public void testGetRaavareBatchListWithraavareId() throws DALException {
		
		List<IngredientBatchDTO> list1 = dao.getIngredientBatchList();
		
		assertTrue(list1.size()>1);
		
	}
	
	@Test
	public void TestCreateRaavareBatch() throws DALException {
		
		List<IngredientBatchDTO> list = dao.getIngredientBatchList();
		int currentList = list.get(list.size()-1).getRbId();
		
		int expected = dao.getIngredientBatchList().size()+1;
		dao.createIngredientBatch(new IngredientBatchDTO(currentList+1, 7, 23));
		int actual = dao.getIngredientBatchList().size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUpdateRaavareBatch() {
		IngredientBatchDTO dto = null;
		int expected = 148;
		try {
			dto = dao.getIngredientBatchList().get(0);
			dto.setIngredientId(expected);
			dao.updateIngredientBatch(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		int actual = dto.getIngredientId();
		assertEquals(expected, actual);
	}

}
