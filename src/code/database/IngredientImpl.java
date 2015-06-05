package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IngredientImpl implements IngredientDAO {

	public IngredientDTO getIngredient(int ingredientId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM ingredient WHERE ingredient_id = " + ingredientId);
	    try {
	    	if (!rs.first()) throw new DALException("ingredient med ID " + ingredientId + " findes ikke");
	    	return new IngredientDTO(rs.getInt("ingredient_id"), rs.getString("ingredient_name"), rs.getString("leverandoer"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public List<IngredientDTO> getIngredientList() throws DALException {
		List<IngredientDTO> list = new ArrayList<IngredientDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ingredient");
		try
		{
			while (rs.next()) 
			{
				list.add(new IngredientDTO(rs.getInt("ingredient_id"), rs.getString("ingredient_name"), rs.getString("leverandoer")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createIngredient(IngredientDTO ingredient) throws DALException {
		Connector.doUpdate(
				"INSERT INTO ingredient(ingredient_id, ingredient_name, leverandoer) VALUES " +
				"(" + ingredient.getIngredientId() + ", '" + ingredient.getIngredientName() + "', '" + ingredient.getLeverandoer() + "')"
			);
		
	}

	public void updateIngredient(IngredientDTO ingredient) throws DALException {
		Connector.doUpdate(
				"UPDATE ingredient SET ingredient_name = '" + ingredient.getIngredientName() + "', leverandoer =  '" + ingredient.getLeverandoer() +"' " + 
				"WHERE ingredient_id = " + ingredient.getIngredientId()
		);
	}

}
