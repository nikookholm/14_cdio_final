package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



public class UserImpl implements UserDAO 
{
	public UserDTO getUser(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM user WHERE opr_id = " + oprId);
	    try {
	    	if (!rs.first()) throw new DALException("Useren " + oprId + " findes ikke");
	    	return new UserDTO (rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("role"), rs.getBoolean("active"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}
	
	public void createUser(UserDTO opr) throws DALException {		
		Connector.doUpdate(
				"INSERT INTO user(opr_id, opr_name, ini, cpr, password, role, active) VALUES " 
						+ "(" + opr.getOprId() + ", '" + opr.getOprName() + "', '" + opr.getIni() + "', '" 
						+ opr.getCpr() + "', '" + opr.getPassword() + "','" + opr.getRole() + "','" + 1 + "')"
		);
	}

	public void updateUser(UserDTO opr) throws DALException {
		Connector.doUpdate(
				"UPDATE user SET opr_name = '" + opr.getOprName() + "', ini = '" + opr.getIni() 
					+ "', cpr = '" + opr.getCpr() + "', password = '" + opr.getPassword() + "', role = '" + opr.getRole() 
					+ "', active = '" + opr.getActive() + "' WHERE opr_id = " + opr.getOprId()
		);
	}
	
	public ArrayList<UserDTO> getUserList() throws DALException {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM user");
		try
		{
			while (rs.next()) 
			{
				list.add(new UserDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("role"), rs.getBoolean("active")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	
	public void deleteUser(UserDTO opr) throws DALException {
		Connector.doUpdate(
				"DELETE user WHERE opr_id = '" + opr.getOprId());
	}
		
		
}
