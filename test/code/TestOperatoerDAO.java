package code;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connector01917.*;
import daoimpl01917.*;
import daointerfaces01917.*;
import dto01917.*;


public class TestOperatoerDAO {
	
	UsersDAO opDao  = new UserImpl();
	
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
		UsersDTO testOperatoer = null;
		List<UsersDTO> operatoerList = opDao.getOperatoerList();
		int ID = operatoerList.get(0).getOprId();
		testOperatoer = opDao.getOperatoer(ID);

		UsersDTO actual = testOperatoer;
		UsersDTO expected = operatoerList.get(0);

		boolean theSame = true;
		
		if (actual.getOprId() 	!= expected.getOprId()) 	   		theSame = false;
		if (!actual.getOprNavn().equals(expected.getOprNavn())) 	theSame = false;
		if (!actual.getCpr().equals(expected.getCpr())) 			theSame = false;
		if (!actual.getIni().equals(expected.getIni())) 			theSame = false;
		if (!actual.getPassword().equals(expected.getPassword())) 	theSame = false;

		assertTrue(theSame);
	}

	@Test
	public void getOperatoerList() throws DALException {
	
		List<UsersDTO> list = opDao.getOperatoerList();
		
		assertTrue(list.size()>1);
	}
	
	@Test
	public void createOperatoer() throws DALException{
		List<UsersDTO> list = opDao.getOperatoerList();
		int currentHighestID  = list.get(list.size()-1).getOprId();
		
		int expected = opDao.getOperatoerList().size()+1;
		opDao.createOperatoer(new UsersDTO(currentHighestID+1, "Jens Jensen", "JJ", "090591-2929", "Ss123Ss"));
		int actual =  opDao.getOperatoerList().size();
		
		

		assertEquals(expected, actual);
	}
	
	@Test
	public void updateOperatoer() throws DALException {
		UsersDTO opDto = null;
		String expected = "Jens Jensen";
		
		try {
			opDto = opDao.getOperatoerList().get(0);
			opDto.setOprNavn(expected);
			opDao.updateOperatoer(opDto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = opDto.getOprNavn();
		assertEquals(expected, actual);
	}

}
