package code;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.database.Connector;
import code.database.DALException;
import code.database.UserDAO;
import code.database.UserDTO;
import code.database.UserImpl;


public class TestOperatoerDAO {
	
	UserDAO opDao  = new UserImpl();
	
	@Before
	public void connect()
	{
		try {
			new Connector();
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void TestGetOperatoer() throws DALException {
		UserDTO testOperatoer = null;
		List<UserDTO> operatoerList = opDao.getUserList();
		int ID = operatoerList.get(0).getOprId();
		testOperatoer = opDao.getUser(ID);

		UserDTO actual = testOperatoer;
		UserDTO expected = operatoerList.get(0);

		boolean theSame = true;
		
		if (actual.getOprId() 	!= expected.getOprId()) 	   		theSame = false;
		if (!actual.getOprName().equals(expected.getOprName())) 	theSame = false;
		if (!actual.getCpr().equals(expected.getCpr())) 			theSame = false;
		if (!actual.getIni().equals(expected.getIni())) 			theSame = false;
		if (!actual.getPassword().equals(expected.getPassword())) 	theSame = false;

		assertTrue(theSame);
	}

	@Test
	public void getOperatoerList() throws DALException {
	
		List<UserDTO> list = opDao.getUserList();
		
		assertTrue(list.size()>1);
	}
	
	@Test
	public void createOperatoer() throws DALException{
		List<UserDTO> list = opDao.getUserList();
		int currentHighestID  = list.get(list.size()-1).getOprId();
		
		int expected = opDao.getUserList().size()+1;
		opDao.createUser(new UserDTO(currentHighestID+1, "Jens Jensen", "JJ", "090591-2929", "Ss123Ss", 1, true));
		int actual =  opDao.getUserList().size();
		
		

		assertEquals(expected, actual);
	}
	
	@Test
	public void updateOperatoer() throws DALException {
		UserDTO opDto = null;
		String expected = "Jens Jensen";
		
		try {
			opDto = opDao.getUserList().get(0);
			opDto.setOprName(expected);
			opDao.updateUser(opDto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = opDto.getOprName();
		assertEquals(expected, actual);
	}

}
