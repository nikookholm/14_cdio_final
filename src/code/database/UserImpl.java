package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class UserImpl implements UserDAO {
	public UserDTO getUser(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM User WHERE opr_id = " + oprId);
	    try {
	    	if (!rs.first()) throw new DALException("Useren " + oprId + " findes ikke");
	    	return new UserDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public void createUser(UserDTO opr) throws DALException {		
			Connector.doUpdate(
				"INSERT INTO User(opr_id, opr_navn, ini, cpr, password) VALUES " +
				"(" + opr.getOprId() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + 
				opr.getCpr() + "', '" + opr.getPassword() + "')"
			);
	}
	
	public void updateUser(UserDTO opr) throws DALException {
		Connector.doUpdate(
				"UPDATE User SET  opr_navn = '" + opr.getOprNavn() + "', ini =  '" + opr.getIni() + 
				"', cpr = '" + opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " +
				opr.getOprId()
		);
	}
	
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM User");
		try
		{
			while (rs.next()) 
			{
				list.add(new UserDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
