package code.database;

import java.util.List;


public interface IngredientDAO {
	IngredientDTO getIngredient(int IngredientId) throws DALException;
	List<IngredientDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO Ingredient) throws DALException;
	void updateIngredient(IngredientDTO Ingredient) throws DALException;
}
