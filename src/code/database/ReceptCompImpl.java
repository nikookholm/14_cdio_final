package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReceptCompImpl implements ReceptCompDAO {

	@Override
	public ReceptCompDTO getReceptComp(int receptId, int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM ReceptComponent WHERE recept_id  =" + receptId + "AND Ingredient_id = " + raavareId);
		try {
			if (!rs.first()) throw new DALException("ReceptComponenten " + receptId + " findes ikke");
			return new ReceptCompDTO (rs.getInt("recept_id"), rs.getInt("Ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptCompDTO> getReceptCompList(int receptId) throws DALException {

		List<ReceptCompDTO> list = new ArrayList<ReceptCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ReceptComponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptCompDTO(rs.getInt("recept_id"), rs.getInt("Ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptCompDTO> getReceptCompList() throws DALException {

		List<ReceptCompDTO> list = new ArrayList<ReceptCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ReceptComponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptCompDTO(rs.getInt("recept_id"), rs.getInt("Ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptComp(ReceptCompDTO ReceptComponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO ReceptComponent(recept_id, Ingredient_id, nom_netto, tolerance) VALUES" +
						"(" + ReceptComponent.getReceptId() + ", '" + ReceptComponent.getRaavareId() + "', '" + ReceptComponent.getNomNetto() + "', '" + 
						ReceptComponent.getTolerance() + ")"
				);

	}

	@Override
	public void updateReceptComp(ReceptCompDTO ReceptComponent) throws DALException {
			Connector.doUpdate(
					"UPDATE ReceptComponent SET recept_id = '" + ReceptComponent.getReceptId() + "', Ingredient_id = '" + ReceptComponent.getRaavareId() + 
					"', nom_netto = '" + ReceptComponent.getNomNetto() + "', tolerance = '" + ReceptComponent.getTolerance() + "' WHERE recept_id = '" +
					ReceptComponent.getReceptId() + "' AND Ingredient_id = '" + ReceptComponent.getRaavareId()); 

	}

}
