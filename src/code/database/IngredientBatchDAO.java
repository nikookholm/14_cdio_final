package code.database;

import java.util.List;

public interface IngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int rbId) throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException;
	void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
}

