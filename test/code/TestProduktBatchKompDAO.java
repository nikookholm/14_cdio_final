package code;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.ProductBatchCompDAO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchCompImpl;

public class TestProduktBatchKompDAO {
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
	public void testGetProduktBachKomp() throws DALException {
		
		ProductBatchCompDAO pbkDAO = null;
		
		List<ProductBatchCompDTO> pbkDTO = dao.getProductBatchCompList();
		int validId = dao.getProductBatchCompList().get(0).getPbId();
		
		List<ProductBatchCompDTO> actual = dao.getProductBatchCompList(validId);
		ProductBatchCompDTO expected = pbkDTO.get(0);
		
		boolean sameEelement = true;
		
		assertTrue(sameEelement);

		
	}


	@Test
	public void testgetProdukBatchKompListPbId() throws DALException {
		
		
		List<ProductBatchCompDTO> list = dao.getProductBatchCompList(1);
		
		assertTrue(list.size()>1);
		
	}

	
	@Test
	public void testProduktBatchKompList() throws DALException {

		
List<ProductBatchCompDTO> list = dao.getProductBatchCompList();
		
		assertTrue(list.size()>1);	
	
	}
	
	
	@Test
	public void testUpdateProduktBatchKomp() throws DALException {
		
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