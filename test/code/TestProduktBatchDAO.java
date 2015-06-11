package code;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.ProductBatchDAO;
import code.database.ProductBatchImpl;
import code.database.*;


public class TestProduktBatchDAO {
	
	ProductBatchDAO pbDao = new ProductBatchImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {
		
		}
	}

	@Test
	public void testGetProduktBatch() throws DALException
	{
		ProductBatchDTO testBatch = null;
		List<ProductBatchDTO> pbList = pbDao.getProductBatchList();
		int validId = pbList.get(0).getPbId();
		testBatch = pbDao.getProductBatch(validId);
		
		ProductBatchDTO actual = testBatch;
		ProductBatchDTO expected = pbList.get(0);
		
		boolean theSame = true;
		
		if (actual.getPbId() 	!= expected.getPbId()) 	   	theSame = false;
		if (actual.getReceptId() != expected.getReceptId()) theSame = false;
		if (actual.getStatus() != expected.getStatus()) 	theSame = false;

		assertTrue(theSame);
		
	}
	
	@Test
	public void testGetProduktBatchList() throws DALException {
		
		List<ProductBatchDTO> pbList = pbDao.getProductBatchList();
		
		assertTrue(pbList.size()>1);
	}
	
	@Test
	public void testCreateProduktBatch() throws DALException {
		List<ProductBatchDTO> pbList = pbDao.getProductBatchList();
		int currentHighestId  = pbList.get(pbList.size()-1).getPbId();
	
		int expected = pbDao.getProductBatchList().size()+1;
		pbDao.createProductBatch(new ProductBatchDTO(currentHighestId+1, 1, 1, 0));
		int actual = pbDao.getProductBatchList().size();
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateProduktBatch() {
		ProductBatchDTO pbList = null;
		int expected = 1;
		try {
			pbList = pbDao.getProductBatchList().get(0);
			pbList.setReceptId(expected);
			pbDao.updateProductBatch(pbList);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int actual = pbList.getReceptId();
		assertEquals(expected, actual);
	}

}
