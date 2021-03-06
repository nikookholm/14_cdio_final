package code;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.ReceptCompDTO;
import code.database.ReceptCompImpl;

public class TestReceptCompDAO {

	ReceptCompImpl rk = new ReceptCompImpl();

	@Before
	public void Connect() {
		try {
			new Connector();
		}catch (Exception e){ }
	}

	@Test
	public void testGetReceptComp() throws DALException
	{
		int actual = rk.getReceptCompList().get(0).getIngredientId();
		int expected = 1;
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetReceptCompList() throws DALException
	{
		boolean listMoreThanZero = false;

		if(rk.getReceptCompList().size() > 0){
			listMoreThanZero = true;
		}
		assertTrue(listMoreThanZero);
	}

	@Test
	public void getReceptCompListWithReceptID() throws DALException
	{
		int receptID = rk.getReceptCompList().get(0).getReceptId();
		
		boolean listMoreThanZero = false;

		if(rk.getReceptCompList(receptID).size() > 0){
			listMoreThanZero = true;
		}
		assertTrue(listMoreThanZero);
	}
	
	@Test
	public void testUpdateReceptComp() throws DALException
	{
		ReceptCompDTO one = null;
		double expected = 0.2;
		
		try{
			one = rk.getReceptCompList().get(0);
			one.setTolerance(expected);
			rk.updateReceptComp(one);
		}catch(DALException e){
			e.printStackTrace();
		}

		double actual = one.getTolerance();
	
		assertEquals(expected, actual, 0);
	}
}
