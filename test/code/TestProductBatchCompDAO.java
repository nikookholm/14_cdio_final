package code;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.ProductBatchCompDAO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchCompImpl;

public class TestProductBatchCompDAO {
	ProductBatchCompDAO dao = new ProductBatchCompImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {
		
		}
	}

	@Test
	public void testGetProductBatchComp() throws DALException {
		
		
		ProductBatchCompDTO pbcDTO = null;
		
		ArrayList<ProductBatchCompDTO> pbkLDTO = dao.getProductBatchCompList();
		
		ProductBatchCompDTO validId = pbkLDTO.get(0);
		
		pbcDTO = dao.getProductBatchComp(validId.getPbId(), validId.getRbId());
		
		
		
		ProductBatchCompDTO actual = pbcDTO;		
		ProductBatchCompDTO expected = pbkLDTO.get(0);
		
		boolean theSame= true;
		
		if(actual.getPbId() != expected.getPbId())
			theSame = false;
		if(actual.getRbId() != expected.getRbId())
			theSame = false;
		if(actual.getOprId() != expected.getOprId())
			theSame = false;
		
		

		assertTrue(theSame);
		


		
	}


	@Test
	public void testGetProductBatchCompListPbId() throws DALException {
		
		
		List<ProductBatchCompDTO> list = dao.getProductBatchCompList(1);
		
		assertTrue(list.size()>1);
		
	}

	
	@Test
	public void testProductBatchCompList() throws DALException {

		
List<ProductBatchCompDTO> list = dao.getProductBatchCompList();
		
		assertTrue(list.size()>1);	
	
	}
	
	
	@Test
	public void testUpdateProductBatchComp() throws DALException {
		
		ProductBatchCompDTO dto = null;
		double expected = 14.8;
		try {
			dto = dao.getProductBatchCompList().get(0);
			dto.setNetto(expected);
			dao.updateProductBatchComp(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		double actual = dto.getNetto();
		
		assertEquals(expected, actual, 0);
	}
	
}