package code;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import code.database.Connector;
import code.database.DALException;
import code.database.ReceptDAO;
import code.database.ReceptDTO;
import code.database.ReceptImpl;

public class TestReceptDAO {

	ReceptDAO re = new ReceptImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) { 
			System.out.println("Catch");
		}
	}

	@Test
	public void testGetRecept() throws DALException{

		ReceptDTO testRec = null;
		List<ReceptDTO> recList = re.getReceptList();
		int ID = recList.get(0).getReceptId();
		testRec = re.getRecept(ID);
		
		ReceptDTO actual = testRec;
		ReceptDTO expected = recList.get(0);

		boolean areSame = true;

		if(actual.getReceptId() != expected.getReceptId()) 				areSame = false;
		if(!actual.getReceptName().equals(expected.getReceptName())) 	areSame = false;

		assertTrue(areSame);
	}
	
	@Test
	public void testGetReceptList() throws DALException{
		
		List<ReceptDTO> recList = re.getReceptList();
		
		assertTrue(recList.size()>1);
	}

	@Test
	public void testCreateRecept() throws DALException{
		
		List<ReceptDTO> recList = re.getReceptList();
		int highID = recList.get(recList.size()-1).getReceptId();
		
		int expected = re.getReceptList().size()+1;
		String Spock = "Spock";
		re.createRecept(new ReceptDTO(Spock, highID+1));
		int actual = re.getReceptList().size();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateRecept()throws DALException{
		
		ReceptDTO rec = null;
		String expected = "Kirk";
		
		rec = re.getReceptList().get(0);
		rec.setReceptName(expected);
		re.updateRecept(rec);
		
		String actual = rec.getReceptName();
		
		assertEquals(expected, actual);
	}

}
