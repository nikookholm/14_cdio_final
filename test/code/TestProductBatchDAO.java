package code;


import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.*;


public class TestProductBatchDAO {
	
	ProductBatchDAO pbDao = new ProductBatchImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {	}
	}

	@Test
	public void testGetProductBatch() throws DALException
	{
		ProductBatchDTO testBatch = null;
		ArrayList<ProductBatchDTO> pbList = pbDao.getProductBatchList();
		int validId = pbList.get(0).getPbId();
		testBatch = pbDao.getProductBatch(validId);
		
		ProductBatchDTO actual = testBatch;
		ProductBatchDTO expected = pbList.get(0);
		
		boolean theSame = true;
		
		if (actual.getPbId() 	!= expected.getPbId()) 	   	theSame = false;
		if (actual.getReceptId() != expected.getReceptId()) theSame = false;
		if (actual.getStatus() 	!= expected.getStatus()) 	theSame = false;

		assertTrue(theSame);
		
	}
	
	@Test
	public void testGetProductBatchList() throws DALException {
		ArrayList<ProductBatchDTO> pbList = pbDao.getProductBatchList();

		assertTrue(pbList.size()>1);
	}
	
	@Test
	public void testCreateProductBatch() throws DALException {
		ArrayList<ProductBatchDTO> pbList = pbDao.getProductBatchList();
		int currentHighestId  = pbList.get(pbList.size()-1).getPbId();
		SimpleDateFormat simpleDateTime = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
		String dateTime = "";
		dateTime = simpleDateTime.format(new Date());
		
		int expected = pbDao.getProductBatchList().size()+1;
		pbDao.createProductBatch(new ProductBatchDTO(currentHighestId+1, 1, 1, dateTime ));
		int actual = pbDao.getProductBatchList().size();
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateProductBatch() {
		
		ProductBatchDTO pbList = null;
		int expected = 1;
		try {
			pbList = pbDao.getProductBatchList().get(0);
			pbList.setReceptId(expected);
			pbDao.updateProductBatch(pbList);
		} catch (DALException e) {
			e.printStackTrace();
		}
		int actual = pbList.getReceptId();
		
		assertEquals(expected, actual);
	}

}
