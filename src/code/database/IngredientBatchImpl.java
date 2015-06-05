package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBatchImpl implements IngredientBatchDAO {

	@Override
	public IngredientBatchDTO getIngredientBatch(int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM Ingredientbatch WHERE rb_id = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("IngredientBatch " + rbId + " findes ikke");
	    	return new IngredientBatchDTO (rs.getInt("rb_id"), rs.getInt("Ingredient_id"), rs.getDouble("maengde"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM Ingredientbatch");
		try
		{
			while (rs.next()) 
			{
				list.add((IngredientBatchDTO) new IngredientBatchDTO (rs.getInt("rb_id"), rs.getInt("Ingredient_id"), rs.getDouble("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int IngredientId)
			throws DALException {
		
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM Ingredient_id WHERE Ingredient_id = '" + IngredientId + "' ");
		try
		{
			while (rs.next()) 
			{
				list.add(new IngredientBatchDTO(rs.getInt("rb_id"), rs.getInt("Ingredient_id"), rs.getDouble("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO Ingredientbatch)
			throws DALException {
		Connector.doUpdate(
				"INSERT INTO Ingredientbatch (rb_id, Ingredient_id, maengde) VALUES " +
				"(" + Ingredientbatch.getRbId() + ", " + Ingredientbatch.getIngredientId() + ", " + Ingredientbatch.getMaengde() + ")"
			);
		
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO Ingredientbatch)
			throws DALException {
		Connector.doUpdate(
				"UPDATE Ingredientbatch SET  rb_id = '" + Ingredientbatch.getRbId() + "', Ingredient_id =  '" + 
						Ingredientbatch.getIngredientId() +"' " + "WHERE maengde = " + Ingredientbatch.getMaengde());
		
	}

}
