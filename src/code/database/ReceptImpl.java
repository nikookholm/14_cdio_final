package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptImpl implements ReceptDAO {
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		
		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
	    try {
	    	if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
	    	return new ReceptDTO (rs.getString("recept_name"), rs.getInt("recept_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public ArrayList<ReceptDTO> getReceptList() throws DALException {
		
		ArrayList<ReceptDTO> list = new ArrayList<ReceptDTO>();
			ResultSet rs = Connector.doQuery("SELECT * FROM recept");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getString("recept_name"), rs.getInt("recept_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		
		Connector.doUpdate(
				"INSERT INTO recept(recept_id, recept_name) VALUES " +
				"(" + recept.getReceptId() + ", '" + recept.getReceptName() + "')"
			);
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate(
				"UPDATE recept SET recept_name = '" + recept.getReceptName() + "' WHERE recept_id = " +
				recept.getReceptId()
		);
	}

}
