package code.database;

import java.util.List;


public interface IngredientDAO {
	IngredientDTO getIngredient(int ingredientId) throws DALException;
	List<IngredientDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO ingredient) throws DALException;
	void updateIngredient(IngredientDTO ingredient) throws DALException;
}
