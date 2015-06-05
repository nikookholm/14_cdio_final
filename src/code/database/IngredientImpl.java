package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IngredientImpl implements IngredientDAO {

	public IngredientDTO getIngredient(int IngredientId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM Ingredient WHERE Ingredient_id = " + IngredientId);
	    try {
	    	if (!rs.first()) throw new DALException("Ingredient med ID " + IngredientId + " findes ikke");
	    	return new IngredientDTO(rs.getInt("Ingredient_id"), rs.getString("Ingredient_navn"), rs.getString("leverandoer"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public List<IngredientDTO> getIngredientList() throws DALException {
		List<IngredientDTO> list = new ArrayList<IngredientDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM Ingredient");
		try
		{
			while (rs.next()) 
			{
				list.add(new IngredientDTO(rs.getInt("Ingredient_id"), rs.getString("Ingredient_navn"), rs.getString("leverandoer")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public void createIngredient(IngredientDTO Ingredient) throws DALException {
		Connector.doUpdate(
				"INSERT INTO Ingredient(Ingredient_id, Ingredient_navn, leverandoer) VALUES " +
				"(" + Ingredient.getIngredientId() + ", '" + Ingredient.getIngredientNavn() + "', '" + Ingredient.getLeverandoer() + "')"
			);
		
	}

	public void updateIngredient(IngredientDTO Ingredient) throws DALException {
		Connector.doUpdate(
				"UPDATE Ingredient SET Ingredient_navn = '" + Ingredient.getIngredientNavn() + "', leverandoer =  '" + Ingredient.getLeverandoer() +"' " + 
				"WHERE Ingredient_id = " + Ingredient.getIngredientId()
		);
	}

}
