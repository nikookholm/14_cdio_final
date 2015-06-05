package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReceptCompImpl implements ReceptCompDAO {

	@Override
	public ReceptCompDTO getReceptComp(int receptId, int ingredientId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM receptcomponent WHERE recept_id  =" + receptId + "AND ingredient_id = " + ingredientId);
		try {
			if (!rs.first()) throw new DALException("ReceptComponenten " + receptId + " findes ikke");
			return new ReceptCompDTO (rs.getInt("recept_id"), rs.getInt("ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptCompDTO> getReceptCompList(int receptId) throws DALException {

		List<ReceptCompDTO> list = new ArrayList<ReceptCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptcomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptCompDTO(rs.getInt("recept_id"), rs.getInt("ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptCompDTO> getReceptCompList() throws DALException {

		List<ReceptCompDTO> list = new ArrayList<ReceptCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptcomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptCompDTO(rs.getInt("recept_id"), rs.getInt("ingredient_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptComp(ReceptCompDTO receptcomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO receptcomponent(recept_id, ingredient_id, nom_netto, tolerance) VALUES" +
						"(" + receptcomponent.getReceptId() + ", '" + receptcomponent.getIngredientId() + "', '" + receptcomponent.getNomNetto() + "', '" + 
						receptcomponent.getTolerance() + ")"
				);

	}

	@Override
	public void updateReceptComp(ReceptCompDTO receptcomponent) throws DALException {
			Connector.doUpdate(
					"UPDATE receptcomponent SET recept_id = '" + receptcomponent.getReceptId() + "', ingredient_id = '" + receptcomponent.getIngredientId() + 
					"', nom_netto = '" + receptcomponent.getNomNetto() + "', tolerance = '" + receptcomponent.getTolerance() + "' WHERE recept_id = '" +
					receptcomponent.getReceptId() + "' AND ingredient_id = '" + receptcomponent.getIngredientId()); 

	}

}
