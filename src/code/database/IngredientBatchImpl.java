package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBatchImpl implements IngredientBatchDAO {

	public IngredientBatchDTO getIngredientBatch(int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM ingredientbatch WHERE rb_id = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("ingredientbatch " + rbId + " findes ikke");
	    	return new IngredientBatchDTO (rs.getInt("rb_id"), rs.getInt("ingredient_id"), rs.getDouble("maengde"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	public ArrayList<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		ArrayList<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ingredientbatch");
		try
		{
			while (rs.next()) 
			{
				list.add((IngredientBatchDTO) new IngredientBatchDTO (rs.getInt("rb_id"), rs.getInt("ingredient_id"), rs.getDouble("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}


	public ArrayList<IngredientBatchDTO> getIngredientBatchList(int ingredientId)
			throws DALException {
		
		ArrayList<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ingredientbatch WHERE ingredient_id = '" + ingredientId + "' ");
		try
		{
			while (rs.next()) 
			{
				list.add(new IngredientBatchDTO(rs.getInt("rb_id"), rs.getInt("ingredient_id"), rs.getDouble("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createIngredientBatch(IngredientBatchDTO ingredientBatch)
			throws DALException {
		Connector.doUpdate(
				"INSERT INTO ingredientbatch (rb_id, ingredient_id, maengde) VALUES " +
				"(" + ingredientBatch.getRbId() + ", " + ingredientBatch.getIngredientId() + ", " + ingredientBatch.getMaengde() + ")"
			);
		
	}

	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch)
			throws DALException {
		Connector.doUpdate(
				"UPDATE ingredientbatch SET  rb_id = '" + ingredientBatch.getRbId() + "', ingredient_id =  '" + 
						ingredientBatch.getIngredientId() +"' " + "WHERE maengde = " + ingredientBatch.getMaengde());
		
	}

}
